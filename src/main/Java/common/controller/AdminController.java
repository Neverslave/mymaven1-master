package common.controller;

import com.jfinal.core.Controller;

public class AdminController extends Controller {

    public void index(){

        render("/adminIndex.html");
    }

}