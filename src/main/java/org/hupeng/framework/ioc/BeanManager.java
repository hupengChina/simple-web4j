package org.hupeng.framework.ioc;

import org.hupeng.framework.ioc.bean.Bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;


public interface BeanManager {

    Set<Bean<?>> getBeans(Type beanType, Annotation... qualifiers);

    Set<Bean<?>> getBeans(String name);

}
