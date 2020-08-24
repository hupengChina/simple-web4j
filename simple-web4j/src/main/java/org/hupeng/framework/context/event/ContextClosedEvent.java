package org.hupeng.framework.context.event;

import org.hupeng.framework.context.ApplicationContext;

/**
 * @author : hupeng
 * @date : 2020/8/24
 */
public class ContextClosedEvent extends ApplicationContextEvent {

    public ContextClosedEvent(ApplicationContext source) {
        super(source);
    }
}
