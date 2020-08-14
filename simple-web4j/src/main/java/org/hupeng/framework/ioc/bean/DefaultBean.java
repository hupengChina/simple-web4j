package org.hupeng.framework.ioc.bean;

import com.sun.istack.internal.Nullable;

import java.util.Set;

/**
 * @Author: hupeng
 * @since: 2019/3/17
 */
public class DefaultBean<T> implements BeanDefinition {

    private String name;

    private Set<FieldBean> fieldValues;

    private Class<T> beanClass;

    @Nullable
    private String initMethodName;

    private boolean synthetic = false;

    public void setSynthetic(boolean synthetic) {
        this.synthetic = synthetic;
    }

    /**
     * Return whether this bean definition is 'synthetic', that is, not defined by the application itself.
     */
    public boolean isSynthetic() {
        return this.synthetic;
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

    public Set<FieldBean> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(Set<FieldBean> fieldValues) {
        this.fieldValues = fieldValues;
    }

    @Override
    public Class<T> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<T> beanClass) {
        this.beanClass = beanClass;
    }

    public DefaultBean(final Class<T> beanClass){
        this.beanClass = beanClass;
        this.name = beanClass.getSimpleName();
    }
}
