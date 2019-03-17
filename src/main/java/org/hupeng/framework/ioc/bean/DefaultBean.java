package org.hupeng.framework.ioc.bean;

import org.hupeng.framework.ioc.point.InjectionPoint;

import java.lang.reflect.Type;
import java.util.Set;

/**
 * @Author: hupeng
 * @since: 2019/3/17 21:56
 */
public class DefaultBean<T> implements Bean {

    private Object instance;

    @Override
    public Set<Type> getTypes() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Class<?> getBeanClass() {
        return null;
    }

    @Override
    public Set<InjectionPoint> getInjectionPoints() {
        return null;
    }

    @Override
    public T getInstance(){
        //获取Bean实例
        return null;
    }
}
