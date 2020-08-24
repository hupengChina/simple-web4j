package org.hupeng.framework.web.event;

import org.hupeng.framework.context.ConfigurableApplicationContext;
import org.hupeng.framework.web.SimpleWebApplication;

/**
 * @author : hupeng
 * @date : 2020/8/24
 */
public class ApplicationStartedEvent extends SimpleWebApplicationEvent {

    private final ConfigurableApplicationContext context;

    public ApplicationStartedEvent(SimpleWebApplication application, String[] args, ConfigurableApplicationContext context) {
        super(application, args);
        this.context = context;
    }

    public ConfigurableApplicationContext getApplicationContext() {
        return this.context;
    }
}
