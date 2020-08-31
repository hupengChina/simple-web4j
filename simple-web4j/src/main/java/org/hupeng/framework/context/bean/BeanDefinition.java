package org.hupeng.framework.context.bean;

import java.util.Collection;

public interface BeanDefinition<T> {

    String getName();

    Class<?> getBeanClass();

    Boolean getBeforeInstantiationResolved();

    void setBeforeInstantiationResolved(Boolean beforeInstantiationResolved);

    Collection<PropertyValue> getPropertyValues();

    boolean isSynthetic();

    boolean isExternallyManagedInitMethod(String initMethod);

    public String getInitMethodName();

}