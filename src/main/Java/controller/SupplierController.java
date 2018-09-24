package controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import intercepter.LoginIntercepter;

@Before(LoginIntercepter.class)
public class SupplierController extends Controller {
    public void index(){

        render("/supplierAdmin.html");
    }

}
