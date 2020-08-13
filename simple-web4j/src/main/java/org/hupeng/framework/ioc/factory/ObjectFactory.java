package org.hupeng.framework.ioc.factory;

@FunctionalInterface
public interface ObjectFactory<T> {

    T getObject();

}