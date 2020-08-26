package org.hupeng.framework.web;

import org.hupeng.framework.context.*;
import org.hupeng.framework.context.factory.BeanDefinitionRegistry;
import org.hupeng.framework.web.listener.EventPublishingRunListener;
import org.hupeng.framework.web.listener.LoggingApplicationListener;
import org.hupeng.framework.web.listener.SimpleWebApplicationRunListener;
import org.hupeng.framework.web.listener.SimpleWebApplicationRunListeners;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author : hupeng
 * @date : 2020/8/5
 */
public class SimpleWebApplication {

    private static final Logger log = LoggerFactory.getLogger(SimpleWebApplication.class);

    private Class<?> primarySource;

    private List<ApplicationListener<?>> listeners;

    private List<ApplicationContextInitializer<?>> initializers = new ArrayList<>();

    public static ConfigurableApplicationContext run(Class<?> primarySource, String... args) {
        return new SimpleWebApplication(primarySource).run(args);
    }

    public SimpleWebApplication(Class<?> primarySource) {
        this.primarySource = primarySource;
        initInitializers();
        initListeners();
    }

    private void initInitializers(){
        //todo
    }

    private void initListeners(){
        setListeners(Arrays.asList(new LoggingApplicationListener()));
    }

    public ConfigurableApplicationContext run(String... args) {

        SimpleWebApplicationRunListeners listeners = getRunListeners(args);
        listeners.starting();
        AnnotationConfigWebServerApplication context = new AnnotationConfigWebServerApplication();
        try {

            prepareContext(context, listeners);

            refreshContext(context);

            listeners.started(context);
        }
        catch (Throwable ex) {
            handleRunFailure(context, listeners, ex);
            throw new IllegalStateException(ex);
        }
		listeners.running(context);
        return context;
    }

    public void setListeners(Collection<? extends ApplicationListener<?>> listeners) {
        this.listeners = new ArrayList<>();
        this.listeners.addAll(listeners);
    }

    public void addListeners(ApplicationListener<?>... listeners) {
        this.listeners.addAll(Arrays.asList(listeners));
    }

    public Set<ApplicationListener<?>> getListeners() {
        return new LinkedHashSet<>(this.listeners);
    }

    private SimpleWebApplicationRunListeners getRunListeners(String[] args) {
        Collection<SimpleWebApplicationRunListener> listeners = new ArrayList<>();
        listeners.add(new EventPublishingRunListener(this,args));
        return new SimpleWebApplicationRunListeners(log, listeners);
    }

    private void prepareContext(ConfigurableApplicationContext context, SimpleWebApplicationRunListeners listeners) {
        applyInitializers(context);
        listeners.contextPrepared(context);
        //载入指定Package下的bean
        load(context, primarySource.getPackage());
        listeners.contextLoaded(context);
    }


    protected void load(ConfigurableApplicationContext context,Object... sources) {
        BeanDefinitionLoader loader = new BeanDefinitionLoader((BeanDefinitionRegistry) context,sources);
        loader.load();
    }

    private void refreshContext(ConfigurableApplicationContext context) {
        context.refresh();
    }

    /**
     * 失败处理
     * @param context
     * @param listeners
     * @param exception
     */
    private void handleRunFailure(ConfigurableApplicationContext context,SimpleWebApplicationRunListeners listeners,Throwable exception) {
        //todo
        listeners.failed(context, exception);
    }

    public void setInitializers(Collection<? extends ApplicationContextInitializer<?>> initializers) {
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
