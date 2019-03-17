package org.hupeng.framework.ioc.bean;

import org.hupeng.framework.ioc.point.InjectionPoint;
import java.lang.reflect.Type;
import java.util.Set;

public interface Bean<T> {

    /**
     * Obtains the {@linkplain: javax.enterprise.inject bean types} of the bean.
     *
     * @return the {@linkplain: javax.enterprise.inject bean types}
     */
    Set<Type> getTypes();

    /**
     * Obtains the {@linkplain: javax.enterprise.inject EL name} of a bean, if it has one.
     *
     * @return the {@linkplain: javax.enterprise.inject EL name}
     */
    String getName();

    /**
     * The bean {@linkplain Class class} of the managed bean or session bean or
     * of the bean that declares the producer method or field.
     *
     * @return the bean {@linkplain Class class}
     */
    Class<?> getBeanClass();

    /**
     * Obtains the {@link org.hupeng.framework.ioc.point.InjectionPoint
     * javax.enterprise.inject.spi.InjectionPoint} objects
     * representing injection points of the bean, that will be validated by the
     * container at initialization time.
     *
     * @return the set of {@linkplain org.hupeng.framework.ioc.point.InjectionPoint
     * javax.enterprise.inject.spi.InjectionPoint injection points} of the bean
     */
    Set<InjectionPoint> getInjectionPoints();

    /**
     * 获取bean实例
     * @return
     */
    public T getInstance();

}