package org.hupeng.framework.context.factory.config;

public interface EarlyReferenceInstantiationAwareBeanPostProcessor {

    default Object getEarlyBeanReference(Object bean, String beanName) {
        return bean;
    }
}
