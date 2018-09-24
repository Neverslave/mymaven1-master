package common;

import com.jfinal.config.*;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.jfinal.template.source.ClassPathSourceFactory;
import controller.LoginController;
import router.AdminRouter;
import router.SupplierRouter;

public class MyConfig extends JFinalConfig {
    @Override
    public void configConstant(Constants me) {
        PropKit.use("config.txt");
        me.setDevMode(PropKit.getBoolean("devMode", true));

    }

    @Override
    public void configRoute(Routes me) {
        me.add(new AdminRouter());
        me.add(new SupplierRouter());
        me.add("/", LoginController.class);

    }

    @Override
    public void configEngine(Engine me) {

    }

    @Override
    public void configPlugin(Plugins me) {
        //配置外部引擎 数据库等
        DruidPlugin dp = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
        me.add(dp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        arp.getEngine().setSourceFactory(new ClassPathSourceFactory()); //设置了模板引擎将从 class path 或者 jar 包中读取 sql 文件
        //arp.setBaseSqlTemplatePath(PathKit.getWebRootPath());//设置sql模板位置
        //arp.addSqlTemplate("common.sql");
        //_MappingKit.mapping(arp);
        //arp.addMapping("user", Blog.class);//表与model相关联 数据库映射需要在加入plugin前
        me.add(arp);

    }

    @Override
    public void configInterceptor(Interceptors me) {

    }

    @Override
    public void configHandler(Handlers me) {

    }
}
