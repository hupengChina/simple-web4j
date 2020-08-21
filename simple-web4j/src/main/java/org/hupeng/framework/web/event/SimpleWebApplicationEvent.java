package org.hupeng.framework.web.event;

import org.hupeng.framework.context.event.ApplicationEvent;
import org.hupeng.framework.web.SimpleWebApplication;

/**
 * @author : hupeng
 * @date : 2020/8/21
 */
public class SimpleWebApplicationEvent extends ApplicationEvent {

    private final String[] args;

    public SimpleWebApplicationEvent(SimpleWebApplication application, String[] args) {
        super(application);
        this.args = args;
    }

    public SimpleWebApplication getSpringApplication() {
        return (SimpleWebApplication) getSource();
    }

    public final String[] getArgs() {
        return this.args;
    }

}
