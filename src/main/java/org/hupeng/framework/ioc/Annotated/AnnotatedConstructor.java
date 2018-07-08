package org.hupeng.framework.ioc.Annotated;

import java.lang.reflect.Constructor;

/**
 * <p>Represents a constructor of a Java class.</p>
 *
 * @param <X> the declaring class
 * @author Gavin King
 * @author Pete Muir
 * @see Constructor
 */
public interface AnnotatedConstructor<X> extends AnnotatedCallable<X> {

    /**
     * <p>Get the underlying {@link Constructor}.</p>
     *
     * @return the constructor
     */
    Constructor<X> getJavaMember();

}
