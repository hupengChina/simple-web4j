package org.hupeng.framework.ioc.factory;

import com.sun.istack.internal.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.hupeng.framework.ioc.Aware;
import org.hupeng.framework.ioc.bean.BeanDefinition;
import org.hupeng.framework.ioc.bean.BeanWrapper;
import org.hupeng.framework.ioc.bean.BeanWrapperImpl;
import org.hupeng.framework.ioc.bean.InitializingBean;
import org.hupeng.framework.ioc.factory.config.BeanPostProcessor;
import org.hupeng.framework.ioc.factory.support.DefaultInstantiationStrategy;
import org.hupeng.framework.ioc.factory.support.InstantiationStrategy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

/**
 * @author : hupeng
 * @date : 2020/8/14
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new DefaultInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition bd, @Nullable Object[] args){
        return doCreateBean(beanName, bd, args);
    }

    protected Object doCreateBean(String beanName, BeanDefinition mbd, @Nullable Object[] args){
        //实例化
        BeanWrapper beanWrapper = createBeanInstance(beanName, mbd, args);
        Object exposedObject = null;
        if(beanWrapper != null){
            exposedObject = beanWrapper.getWrappedInstance();
            //属性填充
            populateBean(beanName, mbd, beanWrapper);
            //初始化
            exposedObject = initializeBean(beanName, exposedObject, mbd);
        }
        return exposedObject;
    }


    protected BeanWrapper createBeanInstance(String beanName, BeanDefinition mbd, @Nullable Object[] args) {

         Object instance = instantiationStrategy.instantiate(mbd,beanName,this);
         return new BeanWrapperImpl().setWrappedInstance(instance);
    }

    /**
     * 属性填充
     * @param beanName
     * @param mbd
     * @param bw
     */
    protected void populateBean(String beanName, BeanDefinition mbd, @Nullable BeanWrapper bw) {
        //todo
    }

    /**
     * 初始化bean
     * @param beanName
     * @param bean
     * @param mbd
     * @return
     */
    protected Object initializeBean(String beanName, Object bean, @Nullable BeanDefinition mbd) {
        invokeAwareMethods(beanName, bean);
        Object wrappedBean = bean;
        if (mbd == null || !mbd.isSynthetic()) {//BeanPostProcessors前置处理
            wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
        }
        //调用初始化方法
        invokeInitMethods(beanName, wrappedBean, mbd);
        if (mbd == null || !mbd.isSynthetic()) {//BeanPostProcessors后置处理
            wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        }

        return wrappedBean;
    }

    /**
     * 调用aware
     * @param beanName
     * @param bean
     */
    private void invokeAwareMethods(String beanName, Object bean) {
        if (bean instanceof Aware) {
            //todo
        }
    }

    /**
     * 调用初始化方法
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
