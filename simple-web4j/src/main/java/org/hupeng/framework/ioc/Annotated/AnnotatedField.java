package org.hupeng.framework.ioc.Annotated;


import java.lang.reflect.Field;


public interface AnnotatedField<X> extends AnnotatedType<X> {

    /**
     * <p>Get the underlying {@link Field}.</p>
     *
     * @return the {@link Field}
     */
    Field getJavaMember();

}
