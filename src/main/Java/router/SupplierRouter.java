package router;

import com.jfinal.config.Routes;
import controller.SupplierController;

public class SupplierRouter extends Routes {
    @Override
    public void config() {

        add("/suppliers", SupplierController.class);
    }
}
