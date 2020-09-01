package org.hupeng.framework.context.factory.config;

public interface AutoProxyInstantiationAwareBeanPostProcessor {

    default Object getEarlyBeanReference(Object bean, String beanName) {
        return bean;
    }
}
