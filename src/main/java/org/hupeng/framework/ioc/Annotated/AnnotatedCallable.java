package org.hupeng.framework.ioc.Annotated;

import java.util.List;

/**
 * <p>Represents a callable member of a Java type.</p>
 *
 * @author Gavin King
 * @author Pete Muir
 *
 * @param <X> the declaring type
 */
public interface AnnotatedCallable<X> extends AnnotatedMember<X>
{

    /**
     * <p>Get the parameters of the callable member.</p>
     *
     * @return the parameters
     */
    List<AnnotatedParameter<X>> getParameters();

}
