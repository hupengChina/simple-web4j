package org.hupeng.framework.ioc.bean;

public interface BeanDefinition<T> {

    String getName();

    Class<?> getBeanClass();

}