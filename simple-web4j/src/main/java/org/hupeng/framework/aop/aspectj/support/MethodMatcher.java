package org.hupeng.framework.aop.aspectj.support;

import com.sun.istack.internal.Nullable;

import java.lang.reflect.Method;

public interface MethodMatcher {

    boolean matches(Method method, @Nullable Class<?> targetClass);

    boolean isRuntime();

    boolean matches(Method method, @Nullable Class<?> targetClass, Object... args);

}
