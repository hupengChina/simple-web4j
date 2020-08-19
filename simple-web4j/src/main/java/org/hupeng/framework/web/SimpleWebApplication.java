package org.hupeng.framework.web;

import org.hupeng.framework.common.Keys;
import org.hupeng.framework.common.util.StringUtil;
import org.hupeng.framework.context.ApplicationContextInitializer;
import org.hupeng.framework.context.ConfigurableApplicationContext;
import org.hupeng.framework.context.DefaultApplicationContext;
import org.hupeng.framework.web.helper.StaticResources;
import org.hupeng.framework.web.server.Server;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : hupeng
 * @date : 2020/8/5
 */
public class SimpleWebApplication {

    private static int port = 80;

    private Class<?> primarySources;

    private List<ApplicationContextInitializer<?>> initializers;

    public static ConfigurableApplicationContext run(Class<?> primarySource, String... args) {
        return new SimpleWebApplication(primarySource).run(args);
    }

    public SimpleWebApplication(Class<?> primarySources) {
        this.primarySources = primarySources;
    }

    public ConfigurableApplicationContext run(String... args) {

        DefaultApplicationContext applicationContext = new DefaultApplicationContext();

        applicationContext.scan(primarySources.getName());
        applicationContext.refresh();

        applyInitializers(applicationContext);

        new WebApplicationLoader().onStartup(applicationContext);

        start();

        return applicationContext;
    }


    private static void start(){
        String portString = StaticResources.getConfigValue(Keys.Server.PORT);
        if(portString != null && StringUtil.isInt(portString)){
            port = Integer.valueOf(portString);
        }
        new Server(port).start();
    }

    public Set<ApplicationContextInitializer<?>> getInitializers() {
        return new LinkedHashSet<>(this.initializers);
    }

    protected void applyInitializers(ConfigurableApplicationContext context) {
        for (ApplicationContextInitializer initializer : getInitializers()) {
            initializer.initialize(context);
        }
    }
}
