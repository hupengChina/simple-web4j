package org.hupeng.framework.context;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.context.event.ApplicationEvent;

public interface GenericApplicationListener extends ApplicationListener<ApplicationEvent> {

    boolean supportsEventType(Class eventType);

    boolean supportsSourceType(@Nullable Class<?> sourceType);

}
