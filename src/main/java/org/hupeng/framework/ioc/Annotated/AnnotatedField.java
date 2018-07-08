package org.hupeng.framework.ioc.Annotated;


import java.lang.reflect.Field;

/**
 * <p>Represents a field of a Java class.</p>
 *
 * @author Gavin King
 * @author Pete Muir
 *
 * @param <X> the declaring type
 * @see Field
 */
public interface AnnotatedField<X> extends AnnotatedMember<X> {

    /**
     * <p>Get the underlying {@link Field}.</p>
     *
     * @return the {@link Field}
     */
    Field getJavaMember();

}
