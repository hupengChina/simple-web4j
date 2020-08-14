package org.hupeng.framework.ioc;

import org.hupeng.framework.ioc.factory.AbstractBeanFactory;

public interface ApplicationContext {

    AbstractBeanFactory getBeanFactory();
}
