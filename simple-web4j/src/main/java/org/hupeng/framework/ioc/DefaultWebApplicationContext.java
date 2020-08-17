package org.hupeng.framework.ioc;

import org.hupeng.framework.ioc.factory.AbstractBeanFactory;
import org.hupeng.framework.ioc.factory.AutowireCapableBeanFactory;
import org.hupeng.framework.ioc.factory.DefaultBeanFactory;
import org.hupeng.framework.ioc.support.DefaultClassScan;

/**
 * @author : hupeng
 * @date : 2020/8/14
 */
public class DefaultWebApplicationContext extends AbstractApplicationContext {

    DefaultBeanFactory beanFactory = new DefaultBeanFactory();

    DefaultClassScan scan = new DefaultClassScan();

    @Override
    public AutowireCapableBeanFactory getAutowireCapableBeanFactory() {
        return beanFactory;
    }

    @Override
    protected void refreshBeanFactory() {

    }
}
