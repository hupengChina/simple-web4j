package org.hupeng.framework.ioc;

import org.hupeng.framework.ioc.factory.AutowireCapableBeanFactory;
import org.hupeng.framework.ioc.factory.HierarchicalBeanFactory;
import org.hupeng.framework.ioc.factory.ListableBeanFactory;

public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory {

    AutowireCapableBeanFactory getAutowireCapableBeanFactory();
}
