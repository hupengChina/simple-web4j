package org.hupeng.framework.ioc.factory;

import com.sun.istack.internal.Nullable;

import java.lang.annotation.Annotation;
import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {

    boolean containsBeanDefinition(String beanName);

    int getBeanDefinitionCount();

    String[] getBeanDefinitionNames();

    String[] getBeanNamesForType(@Nullable Class<?> type);

    <T> Map<String, T> getBeansOfType(@Nullable Class<T> type);

    String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotationType);

    Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType);

    @Nullable
    <A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType);

}
