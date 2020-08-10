package org.hupeng.framework.ioc.support;

import java.util.Collection;
import java.util.List;

public interface WebApplicationContext {

    <T> List<T> getBeans(Class<T> requiredType);

    Object getBean(String name);

    void createBean(Class clazz);

    <T> T getBean(Class<T> requiredType);

    Collection<Class<?>> getClasses();
}
