package org.hupeng.framework.ioc.factory;

public interface BeanFactory {

    Object getBean(String name) ;

    <T> T getBean(String name, Class<T> requiredType);

    Object getBean(String name, Object... args);

    <T> T getBean(Class<T> requiredType);

    <T> T getBean(Class<T> requiredType, Object... args);

    boolean containsBean(String name);
}
