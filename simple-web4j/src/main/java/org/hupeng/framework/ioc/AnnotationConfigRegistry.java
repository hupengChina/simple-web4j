package org.hupeng.framework.ioc;

public interface AnnotationConfigRegistry {

    void register(Class<?>... componentClasses);

    void scan(String... basePackages);
}
