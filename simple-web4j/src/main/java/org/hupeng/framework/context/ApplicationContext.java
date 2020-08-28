package org.hupeng.framework.context;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.context.factory.AutowireCapableBeanFactory;
import org.hupeng.framework.context.factory.HierarchicalBeanFactory;
import org.hupeng.framework.context.factory.ListableBeanFactory;

public interface ApplicationContext extends ApplicationEventPublisher, ListableBeanFactory, HierarchicalBeanFactory {

    @Nullable
    String getId();

    String getApplicationName();

    ApplicationContext getParent();

    AutowireCapableBeanFactory getAutowireCapableBeanFactory();
}
