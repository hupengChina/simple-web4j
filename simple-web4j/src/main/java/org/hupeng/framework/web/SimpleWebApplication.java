package org.hupeng.framework.web;

import org.hupeng.framework.context.AnnotationConfigWebServerApplication;
import org.hupeng.framework.context.ApplicationContextInitializer;
import org.hupeng.framework.context.ConfigurableApplicationContext;
import org.hupeng.framework.context.AnnotationConfigApplicationContext;

import java.util.*;

/**
 * @author : hupeng
 * @date : 2020/8/5
 */
public class SimpleWebApplication {

    private Class<?> primarySources;

    private List<ApplicationContextInitializer<?>> initializers = new ArrayList<>();

    public static ConfigurableApplicationContext run(Class<?> primarySource, String... args) {
        return new SimpleWebApplication(primarySource).run(args);
    }

    public SimpleWebApplication(Class<?> primarySources) {
        this.primarySources = primarySources;
    }

    public ConfigurableApplicationContext run(String... args) {

        AnnotationConfigWebServerApplication applicationContext = new AnnotationConfigWebServerApplication();

        applicationContext.scan(primarySources.getName());
        applicationContext.refresh();

        applyInitializers(applicationContext);

        new SimpleWebApplicationLoader(applicationContext).load();

        return applicationContext;
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
