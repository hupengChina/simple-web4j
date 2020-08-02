package org.hupeng.framework.ioc;

import org.hupeng.framework.ioc.bean.Bean;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Set;


public interface BeanManager {

    /**
     * 生成bean
     * @param beanClass
     * @param <T>
     * @return
     */
    <T> Bean<T> createBean(Class<T> beanClass);

    /**
     * 添加bean
     * @param bean
     */
    void addBean(final Bean<?> bean);

    /**
     * 获取bean
     * @param beanClass
     * @param <T>
     * @return
     */
    <T> Bean<T> getBean(final Class<T> beanClass);

    Collection<Bean<?>> getBeans(final Class<? extends Annotation> stereoType);

}
