package org.hupeng.framework.web;

import org.hupeng.framework.context.DefaultApplicationContext;
import org.hupeng.framework.util.StringUtil;
import org.hupeng.framework.web.helper.StaticResources;
import org.hupeng.framework.web.server.Server;

/**
 * @author : hupeng
 * @date : 2020/8/5
 */
public class SimpleWebApplication {

    private static int port = 80;

    public static void run(Class<?> primarySource, String... args) {
        String basePackages = primarySource.getPackage().getName();
        DefaultApplicationContext applicationContext = new DefaultApplicationContext();
        applicationContext.scan(basePackages);
        applicationContext.refresh();
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
