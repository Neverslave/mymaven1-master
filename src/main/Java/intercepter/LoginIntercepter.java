package intercepter;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.config.Interceptors;
import com.jfinal.core.Controller;

import static common.kit.UrlKit.redirectUrl;
//登录前校验是否存在cookie

public class LoginIntercepter implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
       Controller controller =  inv.getController();
       String username = controller.getCookie("userid");

       if(username ==null) {
           controller.redirect(redirectUrl); //重定向到登录页
           return;

       }

        inv.invoke();
    }
}
