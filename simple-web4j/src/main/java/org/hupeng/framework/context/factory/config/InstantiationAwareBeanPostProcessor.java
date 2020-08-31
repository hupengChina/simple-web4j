package org.hupeng.framework.context.factory.config;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.context.bean.PropertyValue;
import org.hupeng.framework.context.factory.config.BeanPostProcessor;

import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.Collections;

/**
 * @author : hupeng
 * @date : 2020/8/31
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    @Nullable
    default Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
        return null;
    }

    default boolean postProcessAfterInstantiation(Object bean, String beanName) {
        return true;
    }

    @Nullable
    default Collection<PropertyValue> postProcessProperties(Collection<PropertyValue> pvs, Object bean, String beanName) {
        return null;
    }

    @Deprecated
    @Nullable
    default Collection<PropertyValue> postProcessPropertyValues(
            Collection<PropertyValue> pvs, PropertyDescriptor[] pds, Object bean, String beanName) {
        return pvs;
    }

}

