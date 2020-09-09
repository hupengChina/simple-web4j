package org.hupeng.framework.aop.core;

@FunctionalInterface
public interface ClassFilter {

    boolean matches(Class<?> clazz);
}
