package org.hupeng.framework.ioc.bean;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: hupeng
 * @since: 2019/3/17
 */
public class DefaultBeanDefinition<T> implements BeanDefinition {

    private String name;

    private Class<T> beanClass;

    private Collection<PropertyValue> propertyValues = new HashSet<>();

    @Nullable
    private String initMethodName;

    private boolean synthetic = false;

    public void setSynthetic(boolean synthetic) {
        this.synthetic = synthetic;
    }

    public boolean isSynthetic() {
        return this.synthetic;
    }

    public void setPropertyValues(Collection<PropertyValue> propertyValues) {
        this.propertyValues = propertyValues;
    }

    @NotNull
    @Override
    public Collection<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    @Override
    public boolean isExternallyManagedInitMethod(String initMethod) {
        return false;
    }

    @Override
    public String getInitMethodName() {
        return this.initMethodName;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Class<T> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<T> beanClass) {
        this.beanClass = beanClass;
    }

    public DefaultBeanDefinition(final Class<T> beanClass){
        this.beanClass = beanClass;
        this.name = beanClass.getSimpleName();
    }
}
