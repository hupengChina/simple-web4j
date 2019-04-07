package org.hupeng.framework.ioc.bean;

import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import org.apache.commons.lang3.StringUtils;
import org.hupeng.framework.ioc.point.InjectionPoint;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * @Author: hupeng
 * @since: 2019/3/17 21:56
 */
public class DefaultBean<T> implements Bean {

    private Class<T> beanClass;

    private Class<T> proxyClass;

    public DefaultBean(Class<T> beanClass){
        this.beanClass = beanClass;

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setSuperclass(beanClass);
        this.proxyClass = (Class<T>) proxyFactory.createClass();
    }

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
    public T getInstance() throws IllegalAccessException, InstantiationException {
        final T instance = proxyClass.newInstance();
        ((ProxyObject) instance).setHandler((final Object proxy, final Method method, final Method proceed, final Object[] params)-> proceed.invoke(proxy, params));
        return instance;
    }
}
