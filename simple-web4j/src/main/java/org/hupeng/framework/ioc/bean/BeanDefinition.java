package org.hupeng.framework.ioc.bean;

public interface BeanDefinition<T> {

    String getName();

    Class<?> getBeanClass();

    boolean isSynthetic();

    boolean isExternallyManagedInitMethod(String initMethod);

    public String getInitMethodName();

}