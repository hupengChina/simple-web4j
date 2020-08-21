package org.hupeng.framework.web;

import org.hupeng.framework.common.EnvironmentConfig;
import org.hupeng.framework.common.util.StringUtil;
import org.hupeng.framework.context.ApplicationContextInitializer;
import org.hupeng.framework.context.ConfigurableApplicationContext;
import org.hupeng.framework.context.DefaultApplicationContext;
import org.hupeng.framework.web.server.Server;

import java.util.*;

/**
 * @author : hupeng
 * @date : 2020/8/5
 */
public class SimpleWebApplication {

    private static int port = 80;

    private Class<?> primarySources;

    private List<ApplicationContextInitializer<?>> initializers = new ArrayList<>();

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

        startServer();

        return applicationContext;
    }


    private static void startServer(){
        String portString = EnvironmentConfig.Server.getPort();
        if(portString != null && StringUtil.isInt(portString)){
            port = Integer.valueOf(portString);
        }
        new Server(port).start();
    }

    public void setInitializers(
            Collection<? extends ApplicationContextInitializer<?>> initializers) {
        this.initializers = new ArrayList<>();
        this.initializers.addAll(initializers);
    }

    public void addInitializers(ApplicationContextInitializer<?>... initializers) {
        this.initializers.addAll(Arrays.asList(initializers));
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
