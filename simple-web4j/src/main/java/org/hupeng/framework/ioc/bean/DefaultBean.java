package org.hupeng.framework.ioc.bean;

import java.util.Set;

/**
 * @Author: hupeng
 * @since: 2019/3/17
 */
public class DefaultBean<T> implements Bean {

    private String name;

    private Set<FieldBean> fieldValues;

    private Class<T> beanClass;

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
