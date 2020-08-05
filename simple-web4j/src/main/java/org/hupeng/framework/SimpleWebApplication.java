package org.hupeng.framework;

import org.hupeng.framework.helper.StaticResources;
import org.hupeng.framework.ioc.SingletonInstanceContext;
import org.hupeng.framework.util.PropsUtil;
import org.hupeng.framework.util.StringUtil;
import org.hupeng.framework.web.Dispatcher;
import org.hupeng.framework.web.server.Server;

/**
 * @author : hupeng
 * @date : 2020/8/5 11:44
 */
public class SimpleWebApplication {

    private static int port = 80;

    public static void run(Class<?> primarySource, String... args) {
        SingletonInstanceContext.getInstance().init(primarySource.getPackage().getName());
        Dispatcher.init();
        String portString = StaticResources.getConfigValue(Keys.Server.PORT);
        if(portString != null && StringUtil.isInt(portString)){
            port = Integer.valueOf(portString);
        }
        new Server(port).start();
    }
}
