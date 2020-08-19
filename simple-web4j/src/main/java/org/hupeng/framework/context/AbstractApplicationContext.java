package org.hupeng.framework.context;

import org.hupeng.framework.context.factory.AutowireCapableBeanFactory;
import org.hupeng.framework.context.factory.BeanFactory;
import org.hupeng.framework.context.factory.DefaultBeanFactory;
import org.hupeng.framework.context.factory.support.ApplicationContextAwareProcessor;

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

        prepareBeanFactory(beanFactory);
    }

    protected DefaultBeanFactory obtainFreshBeanFactory() {
        return getBeanFactory();
    }

    protected void prepareBeanFactory(DefaultBeanFactory beanFactory) {
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
    }
    @Override
    public abstract DefaultBeanFactory getBeanFactory();

    @Override
    public AutowireCapableBeanFactory getAutowireCapableBeanFactory() {
        return getBeanFactory();
    }

    @Override
    public BeanFactory getParentBeanFactory() {
        return getBeanFactory().getParentBeanFactory();
    }

    @Override
    public boolean containsLocalBean(String name) {
        return getBeanFactory().containsLocalBean(name);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return getBeanFactory().containsLocalBean(beanName);
    }

    @Override
    public int getBeanDefinitionCount() {
        return getBeanFactory().getBeanDefinitionCount();
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        return getBeanFactory().getBeanNamesForType(type);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotationType) {
        return getBeanFactory().getBeanNamesForAnnotation(annotationType);
    }

    @Override
    public Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) {
        return getBeanFactory().getBeansWithAnnotation(annotationType);
    }

    @Override
    public <A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType) {
        return getBeanFactory().findAnnotationOnBean(beanName,annotationType);
    }

    @Override
    public Object getBean(String name) {
        return getBeanFactory().getBean(name);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return getBeanFactory().getBean(name,requiredType);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return getBeanFactory().getBean(name,args);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) {
        return getBeanFactory().getBean(requiredType);
    }

    @Override
    public <T> T getBean(Class<T> requiredType, Object... args) {
        return getBeanFactory().getBean(requiredType,args);
    }

    @Override
    public boolean containsBean(String name) {
        return getBeanFactory().containsBean(name);
    }
}
