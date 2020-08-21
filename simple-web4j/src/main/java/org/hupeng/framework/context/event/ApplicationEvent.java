package org.hupeng.framework.context.event;

import java.util.EventObject;

/**
 * @author : hupeng
 * @date : 2020/8/21
 */
public class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
