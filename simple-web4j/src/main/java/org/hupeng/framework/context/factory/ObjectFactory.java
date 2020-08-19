package org.hupeng.framework.context.factory;

@FunctionalInterface
public interface ObjectFactory<T> {

    T getObject();

}