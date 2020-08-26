package org.hupeng.framework.context.support;

import org.hupeng.framework.context.factory.BeanDefinitionRegistry;

/**
 * @author : hupeng
 * @date : 2020/8/18
 */
public class AnnotatedBeanDefinitionReader {


    private final BeanDefinitionRegistry registry;

    public AnnotatedBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public int register(Class<?>... componentClasses) {
        int count = 0;
        for (Class<?> componentClass : componentClasses) {
            count += registerBean(componentClass);
        }
        return count;
    }

    public int registerBean(Class<?> beanClass) {
        //todo

        return 0;
    }
}
