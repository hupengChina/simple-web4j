package org.hupeng.framework.ioc.support;

import java.util.Collection;
import java.util.List;

public interface WebApplicationContext {

    <T> List<T> getBeans(Class<T> requiredType);

    Object getBean(String name);

    <T> T getBean(Class<T> requiredType) throws InstantiationException, IllegalAccessException;

    Collection<Class<?>> getClasses();
}
