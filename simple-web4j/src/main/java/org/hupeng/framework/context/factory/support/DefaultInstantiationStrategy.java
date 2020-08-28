package org.hupeng.framework.context.factory.support;

import org.hupeng.framework.context.bean.BeanDefinition;
import org.hupeng.framework.context.factory.BeanFactory;
import org.hupeng.framework.commons.util.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author : hupeng
 * @date : 2020/8/14
 */
public class DefaultInstantiationStrategy implements InstantiationStrategy {

    private static final Logger log = LoggerFactory.getLogger(DefaultInstantiationStrategy.class);

    @Override
    public Object instantiate(BeanDefinition bd, String beanName, BeanFactory owner) {
        final Class<?> clazz = bd.getBeanClass();
        if (!clazz.isInterface()) {
            return ReflectionUtil.newInstance(clazz);
        }
        return null;
    }

    @Override
    public Object instantiate(BeanDefinition bd, String beanName, BeanFactory owner, Constructor<?> ctor, Object... args) {//todo
        return null;
    }

    @Override
    public Object instantiate(BeanDefinition bd, String beanName, BeanFactory owner, Object factoryBean, Method factoryMethod, Object... args) {//todo
        return null;
    }
}
