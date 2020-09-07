package org.hupeng.framework.aop.aspectj.support;

@FunctionalInterface
public interface ClassFilter {

    boolean matches(Class<?> clazz);
}
