package common.controller;

import com.jfinal.core.Controller;

public class SupplierController extends Controller {
    public void index(){

        render("/supplierAdmin.html");
    }

}
