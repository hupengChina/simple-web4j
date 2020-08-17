package org.hupeng.framework.ioc;

import org.hupeng.framework.ioc.factory.AutowireCapableBeanFactory;
import org.hupeng.framework.ioc.factory.BeanFactory;
import org.hupeng.framework.ioc.factory.DefaultBeanFactory;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author : hupeng
 * @date : 2020/8/17
 */
public abstract class AbstractApplicationContext implements ConfigurableApplicationContext {



    @Override
    public void refresh(){
        DefaultBeanFactory beanFactory = obtainFreshBeanFactory();

    }

    protected DefaultBeanFactory obtainFreshBeanFactory() {
        refreshBeanFactory();
        return getBeanFactory();
    }

    protected abstract void refreshBeanFactory();

    @Override
    public abstract DefaultBeanFactory getBeanFactory();

    @Override
    public AutowireCapableBeanFactory getAutowireCapableBeanFactory() {
        return null;
    }

    @Override
    public BeanFactory getParentBeanFactory() {
        return null;
    }

    @Override
    public boolean containsLocalBean(String name) {
        return false;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return false;
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

    @Override
    public Object getBean(String name) {
        return null;
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return null;
    }

    @Override
    public Object getBean(String name, Object... args) {
        return null;
    }

    @Override
    public <T> T getBean(Class<T> requiredType) {
        return null;
    }

    @Override
    public <T> T getBean(Class<T> requiredType, Object... args) {
        return null;
    }

    @Override
    public boolean containsBean(String name) {
        return false;
    }
}
