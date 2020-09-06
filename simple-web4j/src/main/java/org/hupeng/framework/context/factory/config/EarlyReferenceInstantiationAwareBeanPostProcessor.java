package org.hupeng.framework.context.factory.config;

public interface EarlyReferenceInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessor{

    default Object getEarlyBeanReference(Object bean, String beanName) {
        return bean;
    }
}
