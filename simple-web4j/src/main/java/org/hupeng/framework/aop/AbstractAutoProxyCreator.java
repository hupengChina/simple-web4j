package org.hupeng.framework.aop;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.context.factory.BeanFactory;
import org.hupeng.framework.context.factory.BeanFactoryAware;
import org.hupeng.framework.context.factory.config.EarlyReferenceInstantiationAwareBeanPostProcessor;

public abstract class AbstractAutoProxyCreator implements EarlyReferenceInstantiationAwareBeanPostProcessor, BeanFactoryAware {

    @Nullable
    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {

        return null;
    }

    @Nullable
    protected BeanFactory getBeanFactory() {
        return this.beanFactory;
    }

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) {
        return null;
    }

}
