package org.hupeng.framework.ioc.Annotated;

import java.util.Set;

/**
 * <p>Represents a Java class or interface.</p>
 *
 * @author Gavin King
 * @author Pete Muir
 *
 * @param <X> the type
 * @see java.lang.Class
 */
public interface AnnotatedType<X> extends Annotated
{

    /**
     * <p>Get the underlying {@link java.lang.Class}.</p>
     *
     * @return the {@link java.lang.Class}
     */
    Class<X> getJavaClass();

    /**
     * <p>Get the {@linkplain AnnotatedConstructor constructors} of the type.
     * If an empty set is returned, a default constructor with no parameters
     * will be assumed.</p>
     *
     * @return the constructors, or an empty set if none are defined
     */
    Set<AnnotatedConstructor<X>> getConstructors();

    /**
     * <p>Get the {@linkplain AnnotatedMethod methods} of the type.</p>
     *
     * @return the methods, or an empty set if none are defined
     */
    Set<AnnotatedMethod<? super X>> getMethods();

    /**
     * <p>Get the {@linkplain AnnotatedField fields} of the type.<p>
     *
     * @return the fields, or an empty set if none are defined
     */
    Set<AnnotatedField<? super X>> getFields();
}