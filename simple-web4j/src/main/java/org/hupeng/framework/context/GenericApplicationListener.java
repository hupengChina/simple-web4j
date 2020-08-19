package org.hupeng.framework.context;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.context.support.ResolvableType;

public interface GenericApplicationListener extends ApplicationListener<ApplicationEvent> {

    boolean supportsEventType(ResolvableType eventType);

    boolean supportsSourceType(@Nullable Class<?> sourceType);

}
