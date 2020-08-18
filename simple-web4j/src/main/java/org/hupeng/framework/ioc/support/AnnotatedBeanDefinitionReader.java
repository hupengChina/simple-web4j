package org.hupeng.framework.ioc.support;

import org.hupeng.framework.ioc.factory.BeanDefinitionRegistry;

/**
 * @author : hupeng
 * @date : 2020/8/18
 */
public class AnnotatedBeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    public AnnotatedBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void register(Class<?>... componentClasses) {
        for (Class<?> componentClass : componentClasses) {
            registerBean(componentClass);
        }
    }

    public void registerBean(Class<?> beanClass) {
        //todo
    }
}
