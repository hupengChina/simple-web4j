package org.hupeng.framework.ioc;

import org.hupeng.framework.ioc.factory.AbstractBeanFactory;
import org.hupeng.framework.ioc.factory.AutowireCapableBeanFactory;

public interface ApplicationContext {

    AutowireCapableBeanFactory getAutowireCapableBeanFactory();
}
