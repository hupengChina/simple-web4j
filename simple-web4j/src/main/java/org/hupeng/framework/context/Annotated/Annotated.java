package org.hupeng.framework.context.Annotated;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * <p>Represents a Java program element that can be annotated.<p>
 *
 * @see java.lang.reflect.AnnotatedElement
 *
 * @author Gavin King
 * @author Pete Muir
 * @author Clint Popetz
 *
 */
public interface Annotated
{

    /**
     *  <p>Get the type of the annotated program element.</p>
     *
     * @return the type of the annotated program element
     */
    public Type getBaseType();

    /**
     *  <p>Get all types to which the base type should be considered
     * assignable.</p>
     *
     * @return a set of all types to which the base type should be considered
     * assignable
     */
    public Set<Type> getTypeClosure();

    /**
     *  <p>Get program element annotation of a certain annotation type.</p>
     *
     * @param <T> the type of the annotation
     * @param annotationType the class of the annotation type
     * @return the program element annotation of the given annotation type,
     * or a null value
     */
    public <T extends Annotation> T getAnnotation(Class<T> annotationType);

    /**
     * <p>Get all annotations of the program element.</p>
     *
     * @return all annotations of the program element, or an empty set if no
     * annotations are present
     */
    public Set<Annotation> getAnnotations();

    /**
     * <p>Determine if the program element has an annotation of a
     * certain annotation type.<p>
     *
     * @param annotationType the annotation type to check for
     * @return <tt>true</tt> if the program element has an annotation of the
     * given annotation type, or <tt>false</tt> otherwise
     */
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationType);
}