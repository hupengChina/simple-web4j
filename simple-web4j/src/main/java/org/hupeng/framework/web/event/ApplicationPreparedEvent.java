package org.hupeng.framework.web.event;

import org.hupeng.framework.context.ConfigurableApplicationContext;
import org.hupeng.framework.web.SimpleWebApplication;

/**
 * @author : hupeng
 * @date : 2020/8/21
 */
public class ApplicationPreparedEvent extends SimpleWebApplicationEvent {

    private final ConfigurableApplicationContext context;

    public ApplicationPreparedEvent(SimpleWebApplication application, String[] args,
                                    ConfigurableApplicationContext context) {
        super(application, args);
        this.context = context;
    }

    public ConfigurableApplicationContext getApplicationContext() {
        return this.context;
    }
}
