package org.hupeng.framework.aop.aspectj.support;

public interface JoinPoint {

    Object proceed() throws Throwable;

    Object getThis();

}
