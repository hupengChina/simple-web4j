package org.hupeng.framework.context;

import org.hupeng.framework.context.support.AnnotatedBeanDefinitionReader;
import org.hupeng.framework.context.support.ClassPathBeanDefinitionScanner;

/**
 * @author : hupeng
 * @date : 2020/8/18
 */
public class AnnotationConfigApplicationContext extends GenericApplicationContext implements AnnotationConfigRegistry{

    private final AnnotatedBeanDefinitionReader reader;

    private final ClassPathBeanDefinitionScanner scanner;

    public AnnotationConfigApplicationContext() {
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
