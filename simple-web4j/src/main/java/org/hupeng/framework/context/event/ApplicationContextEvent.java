package org.hupeng.framework.context.event;

import org.hupeng.framework.context.ApplicationContext;

/**
 * @author : hupeng
 * @date : 2020/8/24
 */
public class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
