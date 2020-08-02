package org.hupeng.framework.ioc.bean;

public interface Bean<T> {

    String getName();

    Class<?> getBeanClass();

}