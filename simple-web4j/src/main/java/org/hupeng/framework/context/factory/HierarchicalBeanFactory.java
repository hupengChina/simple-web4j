package org.hupeng.framework.context.factory;

import com.sun.istack.internal.Nullable;

public interface HierarchicalBeanFactory extends BeanFactory {

    @Nullable
    BeanFactory getParentBeanFactory();

    boolean containsLocalBean(String name);
}
