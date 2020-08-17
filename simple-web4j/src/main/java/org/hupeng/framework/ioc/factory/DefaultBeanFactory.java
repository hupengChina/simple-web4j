package org.hupeng.framework.ioc.factory;

import org.hupeng.framework.ioc.bean.BeanDefinition;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : hupeng
 * @date : 2020/8/13
 */
public class DefaultBeanFactory extends AbstractAutowireCapableBeanFactory implements ListableBeanFactory,BeanDefinitionRegistry{

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public void removeBeanDefinition(String beanName) {
        this.beanDefinitionMap.remove(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return this.beanDefinitionMap.get(beanName);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return this.beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public int getBeanDefinitionCount() {
        return 0;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }

    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        return new String[0];
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return null;
    }

    @Override
    public String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotationType) {
        return new String[0];
    }

    @Override
    public Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) {
        return null;
    }

    @Override
    public <A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType) {
        return null;
    }

}
