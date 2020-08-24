package org.hupeng.framework.context;

import org.hupeng.framework.context.event.ApplicationEvent;

import java.util.EventListener;

/**
 * @author : hupeng
 * @date : 2020/8/19
 */
@FunctionalInterface
public interface ApplicationListener <E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);
}