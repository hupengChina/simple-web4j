package org.hupeng.framework.aop.aspectj.support;

public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
