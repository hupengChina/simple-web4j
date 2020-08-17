package org.hupeng.framework.ioc.backup;

import org.hupeng.framework.ioc.bean.BeanDefinition;

import java.util.Collection;


public interface BeanManager {

    /**
     * 生成bean
     * @param beanClass
     * @param <T>
     * @return
     */
    <T> BeanDefinition<T> createBean(Class<T> beanClass);

    /**
     * 添加bean
     * @param bean
     */
    void addBean(final BeanDefinition<?> bean);

    /**
     * 获取bean
     * @param beanClass
     * @param <T>
     * @return
     */
    <T> BeanDefinition<T> getBean(final Class<T> beanClass);

    Collection<BeanDefinition<?>> getBeans(final Class<?> stereoType);

}
