package org.hupeng.framework.context.logging;

import org.hupeng.framework.context.ApplicationEvent;
import org.hupeng.framework.context.GenericApplicationListener;
import org.hupeng.framework.context.support.ResolvableType;

/**
 * @author : hupeng
 * @date : 2020/8/19
 */
public class LoggingApplicationListener implements GenericApplicationListener {


    @Override
    public boolean supportsEventType(ResolvableType eventType) {
        return false;
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return false;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

    }
}
