package common.router;

import com.jfinal.config.Routes;
import common.controller.SupplierController;

public class SupplierRouter extends Routes {
    @Override
    public void config() {

        add("/supplier", SupplierController.class);
    }
}
