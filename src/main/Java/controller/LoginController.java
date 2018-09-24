package controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.Ret;
import common.kit.IpKit;
import service.LoginService;

public class LoginController extends Controller {
   LoginService loginService = LoginService.loginService;

    //登陆页面
    public void index(){
        render("loginIndex.html");
    }


    //登录校验
    public void loginValidate(){

        String username = getPara("username");
        String password = getPara("password");

        String pwd = HashKit.sha256("saltadmin");
        System.out.println(pwd);
        String loginIp = IpKit.getRealIp(getRequest());
        Ret ret = loginService.login(username,password,loginIp);
        if(ret.isOk()){
            //加入Session
            setSessionAttr("username",username);
            setSessionAttr("role",ret.get("role"));
        }
        renderJson(ret);

    }
}
