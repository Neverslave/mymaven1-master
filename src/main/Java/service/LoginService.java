package service;

import com.jfinal.kit.HashKit;
import com.jfinal.kit.Ret;
import model.Users;
import org.eclipse.jetty.server.Authentication;

import javax.servlet.http.Cookie;
import java.util.Date;

public class LoginService {
    public  static LoginService loginService  = new LoginService();
    private Users usersdao = new Users().dao();


    //登录校验 返回Ret
    public Ret login(String username , String password,String ip){
        Users user = usersdao.findFirst("select * from users where username=? limit 1", username);

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
        String role =user.getRole().toString();
        Date date =  new Date();
        Cookie cookie = new Cookie("username",username);
        cookie.setMaxAge(3600);// 设置为1天 cookie;
        Cookie  cookie1 = new Cookie("updatetime",date.toString());
        cookie1.setMaxAge(3600);
        Cookie cookie2 = new Cookie("role",role);
        cookie2.setMaxAge(3600);
        if(role.equals("1")){
           return Ret.ok("status","1");

        }
        else if(role.equals("2")){
           return   Ret.ok("status","2");
        }




        return Ret.fail("msg","登录失败");

    }
    //更新登录日志
    public void setloginlog(Users user){


    }

}
