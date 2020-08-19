package org.hupeng.framework.context;

import java.util.EventObject;

/**
 * @author : hupeng
 * @date : 2020/8/19
 */
public abstract class ApplicationEvent extends EventObject {

    private final long timestamp;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @param timestamp
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source, long timestamp) {
        super(source);
        this.timestamp = timestamp;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }
}
