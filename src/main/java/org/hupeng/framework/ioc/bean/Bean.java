package org.hupeng.framework.ioc.bean;

import org.hupeng.framework.ioc.context.Contextual;
import org.hupeng.framework.ioc.point.InjectionPoint;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * <p>Represents an {@linkplain: javax.enterprise.inject enabled bean}. This
 * interface defines everything the container needs to manage instances of
 * the bean.</p>
 *
 * @author Gavin King
 * @author David Allen
 * @param <T> the class of the bean instance
 */
public interface Bean<T> extends Contextual<T>
{

    /**
     * Obtains the {@linkplain: javax.enterprise.inject bean types} of the bean.
     *
     * @return the {@linkplain: javax.enterprise.inject bean types}
     */
    Set<Type> getTypes();

    /**
     * Obtains the {@linkplain: javax.inject.Qualifier qualifiers} of the bean.
     *
     * @return the {@linkplain: javax.inject.Qualifier qualifiers}
     */
    Set<Annotation> getQualifiers();

    /**
     * Obtains the {@linkplain: javax.enterprise.context scope} of the bean.
     *
     * @return the {@linkplain: javax.enterprise.context scope}
     */
    Class<? extends Annotation> getScope();

    /**
     * Obtains the {@linkplain: javax.enterprise.inject EL name} of a bean, if it has one.
     *
     * @return the {@linkplain: javax.enterprise.inject EL name}
     */
    String getName();

    /**
     * Obtains the {@linkplain: javax.enterprise.inject.Stereotype stereotypes}
     * of the bean.
     *
     * @return the set of {@linkplain :javax.enterprise.inject.Stereotype stereotypes}
     */
    Set<Class<? extends Annotation>> getStereotypes();

    /**
     * The bean {@linkplain Class class} of the managed bean or session bean or
     * of the bean that declares the producer method or field.
     *
     * @return the bean {@linkplain Class class}
     */
    Class<?> getBeanClass();

    /**
     * Determines if the bean is an
     * {@linkplain: javax.enterprise.inject.Alternative alternative}.
     *
     * @return <tt>true</tt> if the bean is an
     *         {@linkplain :javax.enterprise.inject.Alternative alternative},
     *         and <tt>false</tt> otherwise.
     */
    boolean isAlternative();

    /**
     * Determines if
     * {@link: Contextual#create(CreationalContext)}
     * sometimes return a null value.
     *
     * @return <tt>true</tt> if the {@code create()} method may return a null
     *        value, and <tt>false</tt> otherwise
     */
    boolean isNullable();

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

}