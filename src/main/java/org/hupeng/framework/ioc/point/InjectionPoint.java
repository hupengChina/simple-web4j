package org.hupeng.framework.ioc.point;

import org.hupeng.framework.ioc.Annotated.Annotated;
import org.hupeng.framework.ioc.bean.Bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * JSR-299 SPI.
 *
 * @author Gavin King
 * @author Pete Muir
 */
public interface InjectionPoint
{

    /**
     * Get the required type of injection point.
     *
     * @return the required type
     */
    Type getType();

    /**
     * Get the required qualifiers of the injection point.
     *
     * @return the required qualifiers
     */
    Set<Annotation> getQualifiers();

    /**
     * Get the {@link org.hupeng.framework.ioc.bean.Bean
     * javax.enterprise.inject.spi.Bean} object representing the
     * bean that defines the injection point. If the injection point does not
     * belong to a bean, return a null value.
     *
     * @return the {@link org.hupeng.framework.ioc.bean.Bean
     * javax.enterprise.inject.spi.Bean} object representing
     * bean that defines the injection point, of null if the injection
     * point does not belong to a bean
     */
    Bean<?> getBean();

    /**
     * Get the {@link java.lang.reflect.Field} object in the case of field
     * injection, the {@link java.lang.reflect.Method} object in
     * the case of method parameter injection or the
     * {@link java.lang.reflect.Constructor} object in the case of constructor
     * parameter injection.
     *
     * @return the member
     */
    Member getMember();

    /**
     * Obtain an instance of {@link org.hupeng.framework.ioc.Annotated.AnnotatedField
     * javax.enterprise.inject.spi.AnnotatedField}
     * or {@link org.hupeng.framework.ioc.Annotated.AnnotatedParameter
     * javax.enterprise.inject.spi.AnnotatedParameter}, depending upon
     * whether the injection point is an injected field or a constructor/method parameter.
     *
     * @return an {@code AnnotatedField} or {@code AnnotatedParameter}
     */
    Annotated getAnnotated();

    /**
     * Determines if the injection point is a decorator delegate injection point.
     *
     * @return <tt>true</tt> if the injection point is a decorator delegate injection point,
     * and <tt>false</tt> otherwise
     */
    boolean isDelegate();

    /**
     * Determines if the injection is a transient field.
     *
     * @return <tt>true</tt> if the injection point is a transient field, and <tt>false</tt>
     * otherwise
     */
    boolean isTransient();
}
