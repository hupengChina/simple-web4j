package org.hupeng.framework.ioc.factory;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.ioc.factory.config.BeanPostProcessor;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry{

    void setParentBeanFactory(BeanFactory parentBeanFactory) throws IllegalStateException;

    void setBeanClassLoader(@Nullable ClassLoader beanClassLoader);

    ClassLoader getBeanClassLoader();

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
