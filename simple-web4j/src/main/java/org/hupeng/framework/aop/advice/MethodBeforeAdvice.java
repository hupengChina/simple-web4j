package org.hupeng.framework.aop.advice;

import com.sun.istack.internal.Nullable;

import java.lang.reflect.Method;

public interface MethodBeforeAdvice extends BeforeAdvice {

    void before(Method method, Object[] args, @Nullable Object target) throws Throwable;

}
