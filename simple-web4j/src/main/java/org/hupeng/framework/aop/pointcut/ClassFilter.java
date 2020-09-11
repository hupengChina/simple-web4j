package org.hupeng.framework.aop.pointcut;

@FunctionalInterface
public interface ClassFilter {

    boolean matches(Class<?> clazz);
}
