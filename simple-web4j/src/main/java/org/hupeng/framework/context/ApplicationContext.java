package org.hupeng.framework.context;

import org.hupeng.framework.context.factory.AutowireCapableBeanFactory;
import org.hupeng.framework.context.factory.HierarchicalBeanFactory;
import org.hupeng.framework.context.factory.ListableBeanFactory;

public interface ApplicationContext extends ApplicationEventPublisher, ListableBeanFactory, HierarchicalBeanFactory {

    AutowireCapableBeanFactory getAutowireCapableBeanFactory();
}
