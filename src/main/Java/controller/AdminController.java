package controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.HashKit;
import intercepter.LoginIntercepter;
@Before(LoginIntercepter.class)
public class AdminController extends Controller {
    public void index(){
        String username = getSessionAttr("username");
        String pwd = HashKit.sha256("saltadmin");
        System.out.println(pwd);
        setAttr("username",username);
        render("/adminIndex.html");
    }

}
