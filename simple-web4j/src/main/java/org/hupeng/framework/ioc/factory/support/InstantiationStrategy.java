package org.hupeng.framework.ioc.factory.support;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.ioc.bean.BeanDefinition;
import org.hupeng.framework.ioc.factory.BeanFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public interface InstantiationStrategy {

    Object instantiate(BeanDefinition bd, @Nullable String beanName, BeanFactory owner);

    Object instantiate(BeanDefinition bd, @Nullable String beanName, BeanFactory owner, Constructor<?> ctor, Object... args);

    Object instantiate(BeanDefinition bd, @Nullable String beanName, BeanFactory owner, @Nullable Object factoryBean, Method factoryMethod, Object... args);
}
