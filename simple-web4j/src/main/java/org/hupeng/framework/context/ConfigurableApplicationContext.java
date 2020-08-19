package org.hupeng.framework.context;

import org.hupeng.framework.context.factory.DefaultBeanFactory;

public interface ConfigurableApplicationContext extends ApplicationContext {

    void refresh();

    DefaultBeanFactory getBeanFactory();

}
