package org.hupeng.framework.web.event;

import org.hupeng.framework.context.ConfigurableApplicationContext;
import org.hupeng.framework.web.SimpleWebApplication;

/**
 * @author : hupeng
 * @date : 2020/8/21
 */
public class ApplicationFailedEvent extends SimpleWebApplicationEvent {

    private final ConfigurableApplicationContext context;

    private final Throwable exception;

    public ApplicationFailedEvent(SimpleWebApplication application, String[] args, ConfigurableApplicationContext context, Throwable exception) {
        super(application, args);
        this.context = context;
        this.exception = exception;
    }

    public ConfigurableApplicationContext getApplicationContext() {
        return this.context;
    }

    public Throwable getException() {
        return this.exception;
    }
}
