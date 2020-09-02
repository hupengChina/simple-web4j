package org.hupeng.framework.context.factory;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.context.annotation.Autowired;
import org.hupeng.framework.context.factory.config.InstantiationAwareBeanPostProcessor;
import org.hupeng.framework.context.factory.config.EarlyReferenceInstantiationAwareBeanPostProcessor;
import org.hupeng.framework.context.support.Aware;
import org.hupeng.framework.context.bean.*;
import org.hupeng.framework.context.factory.config.BeanPostProcessor;
import org.hupeng.framework.context.factory.support.DefaultInstantiationStrategy;
import org.hupeng.framework.context.factory.support.InstantiationStrategy;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * @author : hupeng
 * @date : 2020/8/14
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new DefaultInstantiationStrategy();

    private boolean allowRawInjectionDespiteWrapping = false;

    @Override
    protected Object createBean(String beanName, BeanDefinition bd, @Nullable Object[] args){
        //特殊BeanDefinition（如aop场景）实例化方式
        Object bean = resolveBeforeInstantiation(beanName, bd);
        if (bean != null) {
            return bean;
        }
        return doCreateBean(beanName, bd, args);
    }

    protected Object doCreateBean(String beanName, BeanDefinition bd, @Nullable Object[] args){
        //1.实例化
        BeanWrapper beanWrapper = createBeanInstance(beanName, bd, args);
        Object bean = beanWrapper.getWrappedInstance();

        //循环依赖处理
        boolean earlyExposure = isSingletonCurrentlyInCreation(beanName);
        if (earlyExposure) {
            // 最终将调用getEarlyBeanReference，交由对应后置处理器返回
            addSingletonFactory(beanName, () -> getEarlyBeanReference(beanName, bd, bean));
        }

        Object exposedObject = bean;
        if(beanWrapper != null){
            //2.填充属性
            populateBean(beanName, bd, beanWrapper);
            //3.初始化逻辑（Aware注入对象 -> 初始化前BeanPostProcessors -> 初始化方法 -> 初始化后BeanPostProcessors ）
            exposedObject = initializeBean(beanName, exposedObject, bd);
        }

        if (earlyExposure) {//提前引用的情况下
            //判断存在代理对象
            Object earlySingletonReference = getSingleton(beanName,false);
            if (earlySingletonReference != null) {
                if (exposedObject == bean) {//exposedObject未改变
                    exposedObject = earlySingletonReference;
                } else if (!this.allowRawInjectionDespiteWrapping && hasDependentBean(beanName)){

                }
            }
        }

        return exposedObject;
    }


    protected BeanWrapper createBeanInstance(String beanName, BeanDefinition mbd, @Nullable Object[] args) {

         Object instance = instantiationStrategy.instantiate(mbd,beanName,this);
         return new BeanWrapperImpl().setWrappedInstance(instance);
    }

    /**
     * 返回提前暴露的对象，存在后置处理器（如aop代理）交由处理器返回
     * @param beanName
     * @param mbd
     * @param bean
     * @return
     */
    protected Object getEarlyBeanReference(String beanName, BeanDefinition mbd, Object bean) {
        Object exposedObject = bean;
        if (!mbd.isSynthetic() && hasInstantiationAwareBeanPostProcessors()) {
            for (BeanPostProcessor bp : getBeanPostProcessors()) {
                if (bp instanceof EarlyReferenceInstantiationAwareBeanPostProcessor) {
                    //返回代理对象
                    EarlyReferenceInstantiationAwareBeanPostProcessor ibp = (EarlyReferenceInstantiationAwareBeanPostProcessor) bp;
                    exposedObject = ibp.getEarlyBeanReference(exposedObject, beanName);
                }
            }
        }
        return exposedObject;
    }

    /**
     * 属性填充
     * @param beanName
     * @param mbd
     * @param bw
     */
    protected void populateBean(String beanName, BeanDefinition mbd, BeanWrapper bw) {

        Object instance = bw.getWrappedInstance();
        Collection<PropertyValue> propertyValues = mbd.getPropertyValues();
        for (PropertyValue property: propertyValues) {
            Field field = property.getField();
            Autowired autowired = field.getAnnotation(Autowired.class);
            if(autowired != null) {
                String propertyName = field.getType().getSimpleName();
                if (containsBean(propertyName)) {
                    Object bean = getBean(propertyName);
                    try {
                        field.setAccessible(true);
                        field.set(instance, bean);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    registerDependentBean(propertyName, beanName);
                }
            }
        }
    }

    /**
     * 初始化bean
     * @param beanName
     * @param bean
     * @param bd
     * @return
     */
    protected Object initializeBean(String beanName, Object bean, @Nullable BeanDefinition bd) {
        invokeAwareMethods(beanName, bean);
        Object wrappedBean = bean;
        if (bd == null || !bd.isSynthetic()) {//初始化之前的后置处理器
            wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
        }
        //调用初始化方法
        invokeInitMethods(beanName, wrappedBean, bd);
        if (bd == null || !bd.isSynthetic()) {//初始化之后的后置处理器
            wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        }

        return wrappedBean;
    }

    /**
     * 继承了Aware的bean注入对应的对象
     * @param beanName
     * @param bean
     */
    private void invokeAwareMethods(String beanName, Object bean) {
        if (bean instanceof Aware) {
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(AbstractAutowireCapableBeanFactory.this);
            }
            //todo
        }
    }

    /**
     * 初始化方法
     * @param beanName
     * @param bean
     * @param bd
     */
    protected void invokeInitMethods(String beanName, Object bean, @Nullable BeanDefinition bd){
        boolean isInitializingBean = (bean instanceof InitializingBean);
        if (isInitializingBean && (bd == null || !bd.isExternallyManagedInitMethod("afterPropertiesSet"))) {
            try {
                ((InitializingBean) bean).afterPropertiesSet();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (bd != null ) {
            String initMethodName = bd.getInitMethodName();
            if (initMethodName != null && !(isInitializingBean && "afterPropertiesSet".equals(initMethodName)) && !bd.isExternallyManagedInitMethod(initMethodName)) {
                // TODO 自定义初始化方法
            }
        }
    }


    /**
     * BeanDefinition如果标记实例化前解析，直接交给对应的后置处理器返回实例
     * @param beanName
     * @param mbd
     * @return
     */
    @Nullable
    protected Object resolveBeforeInstantiation(String beanName, BeanDefinition mbd) {
        Object bean = null;
        if (Boolean.TRUE.equals(mbd.getBeforeInstantiationResolved())) {
            if (!mbd.isSynthetic() && hasInstantiationAwareBeanPostProcessors()) {
                Class<?> targetType = mbd.getBeanClass();
                if (targetType != null) {
                    bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
                    if (bean != null) {
                        bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
                    }
                }
            }
            mbd.setBeforeInstantiationResolved(bean != null);
        }
        return bean;
    }

    /**
     *
     * 应用【实例化前】后置处理器
     * @param beanClass
     * @param beanName
     * @return
     */
    @Nullable
    protected Object applyBeanPostProcessorsBeforeInstantiation(Class<?> beanClass, String beanName) {
        for (BeanPostProcessor bp : getBeanPostProcessors()) {
            if (bp instanceof InstantiationAwareBeanPostProcessor) {
                InstantiationAwareBeanPostProcessor ibp = (InstantiationAwareBeanPostProcessor) bp;
                Object result = ibp.postProcessBeforeInstantiation(beanClass, beanName);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }


    /**
     * 应用【初始化前】后置处理器
     * @param existingBean
     * @param beanName
     * @return
     */
    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    /**
     * 应用【初始化后】后置处理器
     * @param existingBean
     * @param beanName
     * @return
     */
    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }
}
