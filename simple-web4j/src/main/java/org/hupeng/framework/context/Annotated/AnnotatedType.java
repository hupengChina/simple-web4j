package org.hupeng.framework.context.Annotated;

import java.util.Set;


public interface AnnotatedType<X> extends Annotated {

    Class<X> getJavaClass();

    Set<AnnotatedField<? super X>> getFields();
}