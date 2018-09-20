package common.router;

import com.jfinal.config.Routes;
import common.controller.AdminController;
import common.controller.LoginController;

public class AdminRouter extends Routes {
    @Override
    public void config() {
        add("/admin", AdminController.class);
    }
}
