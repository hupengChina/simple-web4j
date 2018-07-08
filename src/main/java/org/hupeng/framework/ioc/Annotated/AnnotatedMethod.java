package org.hupeng.framework.ioc.Annotated;

import java.lang.reflect.Method;

/**
 * <p>Represents a method of a Java type.</p>
 *
 * @param <X> the declaring type
 * @author Gavin King
 * @author Pete Muir
 * @see Method
 */
public interface AnnotatedMethod<X> extends AnnotatedCallable<X> {

    /**
     * <p>Get the underlying {@link Method}.</p>
     *
     * @return the {@link Method}
     */
    Method getJavaMember();
}
