package intercepter;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.config.Interceptors;
import com.jfinal.core.Controller;

public class LoginIntercepter implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
       Controller controller =  inv.getController();
       String username = controller.getCookie("username");

       if(username ==null) {
           System.out.println("重定向");
           controller.redirect("");

       }

        inv.invoke();
    }
}
