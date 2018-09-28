package service;

import com.jfinal.kit.HashKit;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import model.Session;
import model.User;

import java.util.Date;

public class LoginService {
    public  static LoginService loginService  = new LoginService();
    // 存放登录用户的 cacheName
    public static final String loginAccountCacheName = "loginAccount";

    // "userid" 仅用于 cookie 名称，其它地方如 cache 中全部用的 "sessionId" 来做 key
    public static final String sessionIdName = "userid";
    private User userdao = new User().dao();


    //登录校验 返回Ret
    public Ret login(String username , String password,String ip){
        User user = userdao.findFirst("select * from user where username=? limit 1", username);

        //若未找到账号
        if(user == null){
            return Ret.fail("msg","用户名不存在");
        }

        String salt = user.getSalt();
        String hashpassword = HashKit.sha256(salt+password);
        //密码校验失败
        if(user.getPassword().equals(hashpassword) == false){
            return Ret.fail("msg","密码不正确");
        }
        long liveseconds =120*60; // Session 存放时间120分钟
        long expireAt = System.currentTimeMillis()+(liveseconds*1000);
         //保存登录的session到数据库
        Session session = new Session();
        String sessionId = StrKit.getRandomUUID();
        session.setSessionId(sessionId);
        session.setUserid(user.getId());
        session.setExpireAt(expireAt);
        if ( ! session.save()) {
            return Ret.fail("msg", "保存 session 到数据库失败，请联系管理员");
        }
        setloginlog(user.getId(),ip);
        //获取角色
        String role =user.getRole().toString();
        if(role.equals("1")){
           return Ret.ok("status","1")
                   .set(sessionIdName,sessionId)
                   .set("maxAgeInSeconds", liveseconds);
        }
        else if(role.equals("2")){
           return   Ret.ok("status","2")
                   .set(sessionIdName,sessionId)
                   .set("maxAgeInSeconds", liveseconds);
        }

        return Ret.fail("msg","登录失败");

    }
    //创建登录日志
    public void setloginlog(String id , String ip){
        Record loginlog = new Record().set("id",id).set("ip",ip).set("logintime",new Date());
        Db.save("login_log",loginlog);
    }


    /**
     * 通过 sessionId 获取登录用户信息
     * sessoin表结构：session(id, userid, expireAt)
     *
     * //1：先从缓存里面取，如果取到则返回该值，如果没取到则从数据库里面取(暂未使用缓存）
     * 2：在数据库里面取，如果取到了，则检测是否已过期，如果过期则清除记录，
     *     如果没过期则先放缓存一份，然后再返回
     */
    public User loginWithSessionId(String sessionid,String loginIp){
        Session session = Session.dao.findById(sessionid);
        if (session == null) {      // session 不存在
            return null;
        }
        if (session.isExpired()) {  // session 已过期
            session.delete();		// 被动式删除过期数据，此外还需要定时线程来主动清除过期数据
            return null;
        }
        User user = userdao.findById(session.getUserid());
        // 找到 loginAccount 并且 是正常状态 才允许登录
        if (user!= null ) {
            user.removeSensitiveInfo();                                 // 移除 password 与 salt 属性值
            user.put("sessionId", sessionid);                          // 保存一份 sessionId 到 loginAccount 备用
           // CacheKit.put(loginAccountCacheName, sessionId, loginAccount);

           setloginlog(user.getId(), loginIp);
            return user;
        }
        return null;

    }

    /**
     * 退出登录
     */
    public void logout(String sessionId) {
        if (sessionId != null) {
            Session.dao.deleteById(sessionId);
        }
    }

}
