package controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.Ret;
import common.kit.IpKit;
import intercepter.LoginValidator;
import model.User;
import service.LoginService;

public class LoginController extends Controller {
   LoginService loginService = LoginService.loginService;

    //登陆页面
    public void index(){
        render("loginIndex.html");
    }
    //登录校验

    @Before(LoginValidator.class)
    public void loginValidate(){

        String username = getPara("username");
        String password = getPara("password");

        String pwd = HashKit.sha256("saltadmin");
        System.out.println(pwd);
        String loginIp = IpKit.getRealIp(getRequest());
        Ret ret = loginService.login(username,password,loginIp);
        if(ret.isOk()){
            String sessionid = ret.getStr(LoginService.sessionIdName);
            int maxAgeInSeconds = ret.getInt("maxAgeInSeconds");
            setCookie(LoginService.sessionIdName, sessionid, maxAgeInSeconds, true);
            setCookie("role",ret.getStr("sataus"),maxAgeInSeconds,true);
            setAttr(LoginService.sessionIdName,sessionid);
        }
        renderJson(ret);

    }	/**
     * 退出登录
     */
    @Clear
    @ActionKey("/logout")
    public void logout() {
        loginService.logout(getCookie(LoginService.sessionIdName));
        removeCookie(LoginService.sessionIdName);
        redirect("/");
    }

    //验证码服务
    public void captcha(){

        renderCaptcha();

    }


   public void  updateLogintime(User user){



   }

   public  void  gete(){
        render("/baseAdmin.html");
   }

}
