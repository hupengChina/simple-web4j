package org.hupeng.framework;

import org.hupeng.framework.helper.StaticResources;
import org.hupeng.framework.ioc.SingletonWebApplicationContext;
import org.hupeng.framework.ioc.support.WebApplicationContext;
import org.hupeng.framework.util.StringUtil;
import org.hupeng.framework.web.Dispatcher;
import org.hupeng.framework.web.WebApplicationLoader;
import org.hupeng.framework.web.server.Server;

/**
 * @author : hupeng
 * @date : 2020/8/5
 */
public class SimpleWebApplication {

    private static int port = 80;

    public static void run(Class<?> primarySource, String... args) {
        SingletonWebApplicationContext applicationContext = SingletonWebApplicationContext.getInstance();
        applicationContext.init(primarySource.getPackage().getName());
        new WebApplicationLoader().onStartup(applicationContext);
        start();
    }


    private static void start(){
        String portString = StaticResources.getConfigValue(Keys.Server.PORT);
        if(portString != null && StringUtil.isInt(portString)){
            port = Integer.valueOf(portString);
        }
        new Server(port).start();
    }
}
