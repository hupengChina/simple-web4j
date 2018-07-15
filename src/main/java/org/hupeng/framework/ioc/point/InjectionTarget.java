package org.hupeng.framework.ioc.point;

import org.hupeng.framework.ioc.context.CreationalContext;

/**
 * <p>
 * Provides operations for performing
 * {@linkplain :javax.enterprise.inject dependency injection} and
 * lifecycle callbacks on an instance of a type.
 * </p>
 *
 * @see javax.annotation.PostConstruct
 * @see javax.annotation.PreDestroy
 *
 * @author Pete Muir
 * @author David Allen
 * @param <T> The class of the instance
 */
public interface InjectionTarget<T> extends Producer<T>
{

    /**
     * <p>
     * Performs dependency injection upon the given object. Performs Java EE
     * component environment injection, sets the value of all injected fields,
     * and calls all initializer methods.
     * </p>
     *
     * @param instance The instance upon which to perform injection
     * @param ctx The {@link javax.enterprise.context.spi.CreationalContext} to
     *           use for creating new instances
     */
    void inject(T instance, CreationalContext<T> ctx);

    /**
     * <p>
     * Calls the {@link javax.annotation.PostConstruct} callback, if it exists,
     * according to the semantics required by the Java EE platform specification.
     * </p>
     *
     * @param instance The instance on which to invoke the
     *           {@link javax.annotation.PostConstruct} method
     */
    void postConstruct(T instance);

    /**
     * <p>
     * Calls the {@link javax.annotation.PreDestroy} callback, if it exists,
     * according to the semantics required by the Java EE platform specification.
     * </p>
     *
     * @param instance The instance on which to invoke the
     *           {@link javax.annotation.PreDestroy} method
     */
    void preDestroy(T instance);

}