package org.hupeng.framework.context;

import org.hupeng.framework.context.event.ApplicationEvent;

public interface ApplicationEventPublisher {

    default void publishEvent(ApplicationEvent event) {
        publishEvent((Object) event);
    }

    void publishEvent(Object event);
}
