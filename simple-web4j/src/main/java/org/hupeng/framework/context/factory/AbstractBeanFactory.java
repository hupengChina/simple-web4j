package org.hupeng.framework.context.factory;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.context.bean.BeanDefinition;
import org.hupeng.framework.context.factory.config.BeanPostProcessor;
import org.hupeng.framework.commons.util.ClassUtil;
import org.hupeng.framework.context.factory.config.InstantiationAwareBeanPostProcessor;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author : hupeng
 * @date : 2020/8/13
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private volatile boolean hasInstantiationAwareBeanPostProcessors = false;

    @Nullable
    private BeanFactory parentBeanFactory;

    private ClassLoader beanClassLoader = ClassUtil.getClassLoader();

    //创建过的beanName集合
    private final Set<String> alreadyCreated = Collections.newSetFromMap(new ConcurrentHashMap<>(256));

    private final List<BeanPostProcessor> beanPostProcessors = new CopyOnWriteArrayList<>();

    @Override
    public Object getBean(String name) {
        return doGetBean(name, null, null, false);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return doGetBean(name, requiredType, null, false);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return doGetBean(name, null, args, false);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) {
        return doGetBean(requiredType.getSimpleName(), requiredType, null, false);
    }

    @Override
    public <T> T getBean(Class<T> requiredType, Object... args) {
        return doGetBean(requiredType.getSimpleName(), requiredType, args, false);
    }

    @Override
    public boolean containsBean(String name) {
        return containsSingleton(name) || containsBeanDefinition(name);
    }

    protected abstract boolean containsBeanDefinition(String beanName);

    protected abstract BeanDefinition getBeanDefinition(String beanName);

    protected abstract Object createBean(String beanName, BeanDefinition mbd, @Nullable Object[] args);

    protected <T> T doGetBean(String beanName, @Nullable Class<T> requiredType, @Nullable Object[] args, boolean typeCheckOnly){
        //先从缓存中取，循环依赖的时候
        Object bean = getSingleton(beanName);
        if (bean != null) {
            return (T) bean;
        }

        //当前容器不存在，如果存在父容器，则递归
        BeanFactory parentBeanFactory = getParentBeanFactory();
        if (parentBeanFactory != null && !containsBeanDefinition(beanName)) {
            if (args != null) {
                return (T) parentBeanFactory.getBean(beanName, args);
            }
            else if (requiredType != null) {
                return parentBeanFactory.getBean(beanName, requiredType);
            }
            else {
                return (T) parentBeanFactory.getBean(beanName);
            }
        }

        //beanName放入已创建集合做标记
        if (!typeCheckOnly) {
            markBeanAsCreated(beanName);
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        //创建bean
        bean = getSingleton(beanName, () -> {
            return createBean(beanName, beanDefinition, args);
        });
        return (T) bean;

    }

    protected void markBeanAsCreated(String beanName) {
        if (!this.alreadyCreated.contains(beanName)) {
            this.alreadyCreated.add(beanName);
        }
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    protected boolean hasInstantiationAwareBeanPostProcessors() {
        return this.hasInstantiationAwareBeanPostProcessors;
    }

    @Override
    public void setParentBeanFactory(BeanFactory parentBeanFactory) throws IllegalStateException {
        this.parentBeanFactory = parentBeanFactory;
    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
            this.hasInstantiationAwareBeanPostProcessors = true;
        }
        this.beanPostProcessors.add(beanPostProcessor);
    }

    @Override
    public BeanFactory getParentBeanFactory() {
        return this.parentBeanFactory;
    }

    @Override
    public boolean containsLocalBean(String beanName) {
        return containsSingleton(beanName) || containsBeanDefinition(beanName);
    }
}
