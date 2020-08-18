package org.hupeng.framework.ioc;

import org.hupeng.framework.ioc.support.AnnotatedBeanDefinitionReader;
import org.hupeng.framework.ioc.support.ClassPathBeanDefinitionScanner;

/**
 * @author : hupeng
 * @date : 2020/8/18
 */
public class DefaultApplicationContext extends GenericApplicationContext implements AnnotationConfigRegistry{

    private final AnnotatedBeanDefinitionReader reader;

    private final ClassPathBeanDefinitionScanner scanner;

    public DefaultApplicationContext() {
        this.reader = new AnnotatedBeanDefinitionReader(this);
        this.scanner = new ClassPathBeanDefinitionScanner(this);
    }

    @Override
    public void register(Class<?>... componentClasses) {
        reader.register(componentClasses);
    }

    @Override
    public void scan(String... basePackages) {
        scanner.scan(basePackages);
    }
}
