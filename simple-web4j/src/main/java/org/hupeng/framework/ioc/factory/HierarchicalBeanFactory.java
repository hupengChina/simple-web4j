package org.hupeng.framework.ioc.factory;

import com.sun.istack.internal.Nullable;

public interface HierarchicalBeanFactory extends BeanFactory {

    @Nullable
    BeanFactory getParentBeanFactory();

    boolean containsLocalBean(String name);
}
