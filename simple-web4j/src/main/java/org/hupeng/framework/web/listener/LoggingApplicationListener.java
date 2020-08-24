package org.hupeng.framework.web.listener;

import org.hupeng.framework.common.logging.LogbackLoggingSystem;
import org.hupeng.framework.common.logging.LoggingSystem;
import org.hupeng.framework.context.GenericApplicationListener;
import org.hupeng.framework.context.event.ApplicationEvent;
import org.hupeng.framework.context.event.ContextClosedEvent;
import org.hupeng.framework.context.factory.DefaultBeanFactory;
import org.hupeng.framework.web.event.ApplicationFailedEvent;
import org.hupeng.framework.web.event.ApplicationPreparedEvent;
import org.hupeng.framework.web.event.ApplicationStartingEvent;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author : hupeng
 * @date : 2020/8/19
 */
public class LoggingApplicationListener implements GenericApplicationListener {

    private LoggingSystem loggingSystem;

    public static final String LOGGING_SYSTEM_BEAN_NAME = "SimpleWebLoggingSystem";

    private static final Collection<Class<?>> EVENT_TYPES = new ArrayList<>();

    static {
        EVENT_TYPES.add(ApplicationStartingEvent.class);
        EVENT_TYPES.add(ApplicationPreparedEvent.class);
        EVENT_TYPES.add(ContextClosedEvent.class);
        EVENT_TYPES.add(ApplicationFailedEvent.class);
    }

    @Override
    public boolean supportsEventType(Class eventType) {
        return EVENT_TYPES.contains(eventType);
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return false;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationStartingEvent) {
            onApplicationStartingEvent((ApplicationStartingEvent) event);
            return;
        }
        if (event instanceof ApplicationPreparedEvent) {
            onApplicationPreparedEvent((ApplicationPreparedEvent) event);
            return;
        }
        if (event instanceof ContextClosedEvent) {
            onContextClosedEvent();
        }
        if (event instanceof ApplicationFailedEvent) {
            onApplicationFailedEvent();
            return;
        }
    }

    private void onApplicationStartingEvent(ApplicationStartingEvent event) {
        this.loggingSystem = new LogbackLoggingSystem();
        this.loggingSystem.beforeInitialize();
    }

    private void onApplicationPreparedEvent(ApplicationPreparedEvent event) {
        this.loggingSystem.initialize();
        DefaultBeanFactory beanFactory = event.getApplicationContext().getBeanFactory();
        if (!beanFactory.containsBean(LOGGING_SYSTEM_BEAN_NAME)) {
            beanFactory.registerSingleton(LOGGING_SYSTEM_BEAN_NAME, this.loggingSystem);
        }
    }

    private void onContextClosedEvent() {
        if (this.loggingSystem != null) {
            this.loggingSystem.cleanUp();
        }
    }

    private void onApplicationFailedEvent() {
        if (this.loggingSystem != null) {
            this.loggingSystem.cleanUp();
        }
    }
}
