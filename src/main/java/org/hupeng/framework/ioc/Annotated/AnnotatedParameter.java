package org.hupeng.framework.ioc.Annotated;

/**
 * <p>Represents a parameter of a method or constructor.</p>
 *
 * @param <X> the type that declares the method or constructor
 * @author Gavin King
 * @author Pete Muir
 */
public interface AnnotatedParameter<X> extends Annotated {

    /**
     * <p>Get the position of the parameter in the method or
     * constructor argument list.</p>
     *
     * @return the position of the parameter
     */
    int getPosition();

    /**
     * <p>Get the declaring {@linkplain AnnotatedCallable method or
     * constructor}.</p>
     *
     * @return the declaring callable
     */
    AnnotatedCallable<X> getDeclaringCallable();

}
