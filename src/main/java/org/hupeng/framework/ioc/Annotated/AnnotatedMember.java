package org.hupeng.framework.ioc.Annotated;

import java.lang.reflect.Member;

/**
 * <p>Represents a member of a Java type.</p>
 *
 * @param <X> the declaring type
 * @author Gavin King
 * @author Pete Muir
 * @see Member
 */
public interface AnnotatedMember<X> extends Annotated {

    /**
     * <p>Get the underlying {@link Member}.</p>
     *
     * @return the {@link Member}
     */
    Member getJavaMember();

    /**
     * <p>Determines if the member is static.</p>
     *
     * @return <tt>true</tt> if the member is static
     */
    boolean isStatic();

    /**
     * <p>Get the {@linkplain AnnotatedType type} which declares this
     * member.</p>
     *
     * @return the type which declares this member
     */
    AnnotatedType<X> getDeclaringType();
}
