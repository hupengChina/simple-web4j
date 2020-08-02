package org.hupeng.framework.ioc.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * @Author: hupeng
 * @since: 2019/3/17 21:56
 */
@Slf4j
@Data
public class DefaultBean<T> implements Bean {

    private String name;

    private Set<FieldBean> fieldValues;

    private Class<T> beanClass;


    public DefaultBean(final Class<T> beanClass){
        this.beanClass = beanClass;
        this.name = beanClass.getSimpleName();
    }
}
