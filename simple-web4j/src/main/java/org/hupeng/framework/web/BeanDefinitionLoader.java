package org.hupeng.framework.web;

import org.hupeng.framework.context.factory.BeanDefinitionRegistry;
import org.hupeng.framework.context.support.AnnotatedBeanDefinitionReader;
import org.hupeng.framework.context.support.ClassPathBeanDefinitionScanner;

/**
 * @author : hupeng
 * @date : 2020/8/25
 */
public class BeanDefinitionLoader {

    private final Object[] sources;

    private final AnnotatedBeanDefinitionReader annotatedReader;

    private final ClassPathBeanDefinitionScanner scanner;

    BeanDefinitionLoader(BeanDefinitionRegistry registry, Object... sources) {
        this.sources = sources;
        this.annotatedReader = new AnnotatedBeanDefinitionReader(registry);
        this.scanner = new ClassPathBeanDefinitionScanner(registry);
    }

    int load() {
        int count = 0;
        for (Object source : this.sources) {
            count += load(source);
        }
        return count;
    }

    private int load(Object source) {
        //todo 后续扩展其他类型
        if(source instanceof Package) {
            return load((Package)source);
        }
        if(source instanceof Class) {
            return load((Class)source);
        }
        throw new IllegalArgumentException("不支持当前类加载Bean" + source.getClass());
    }

    private int load(Package source) {
         return this.scanner.scan(source.getName());
    }

    private int load(Class source) {
        return this.annotatedReader.register(source);
    }
}
