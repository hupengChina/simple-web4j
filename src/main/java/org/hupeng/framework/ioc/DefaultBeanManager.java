package org.hupeng.framework.ioc;


import org.hupeng.framework.ioc.bean.Bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * @author hupeng
 * @since 2019/3/10.
 */
public class DefaultBeanManager implements BeanManager {


    @Override
    public Set<Bean<?>> getBeans(Type beanType, Annotation... qualifiers) {
        return null;
    }

    @Override
    public Set<Bean<?>> getBeans(String name) {
        return null;
    }
}
