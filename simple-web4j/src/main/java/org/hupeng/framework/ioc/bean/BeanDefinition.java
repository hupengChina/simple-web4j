package org.hupeng.framework.ioc.bean;

import java.util.Collection;

public interface BeanDefinition<T> {

    String getName();

    Class<?> getBeanClass();

    Collection<PropertyValue> getPropertyValues();

    boolean isSynthetic();

    boolean isExternallyManagedInitMethod(String initMethod);

    public String getInitMethodName();

}