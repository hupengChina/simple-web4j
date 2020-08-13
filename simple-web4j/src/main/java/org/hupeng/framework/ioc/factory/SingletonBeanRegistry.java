package org.hupeng.framework.ioc.factory;

import com.sun.istack.internal.Nullable;

public interface SingletonBeanRegistry {

    void registerSingleton(String beanName, Object singletonObject);

    @Nullable
    Object getSingleton(String beanName);

    boolean containsSingleton(String beanName);

}
