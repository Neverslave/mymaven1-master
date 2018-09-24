package controller;

import com.jfinal.core.Controller;

public class LoginController extends Controller {

    //登陆页面
    public void index(){
        render("loginIndex.html");
    }
    //登录校验
    public void  loginValidate(){
        String username = getPara("username");
        String password = getPara("password");
        System.out.println(username+" "+password);
        render("");


    }
}
