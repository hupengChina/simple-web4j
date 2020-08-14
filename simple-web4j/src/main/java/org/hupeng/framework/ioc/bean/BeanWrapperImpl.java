package org.hupeng.framework.ioc.bean;

import com.sun.istack.internal.Nullable;

/**
 * @author : hupeng
 * @date : 2020/8/14
 */
public class BeanWrapperImpl implements BeanWrapper {

    @Nullable
    Object wrappedObject;

    public BeanWrapper setWrappedInstance(Object object){
        this.wrappedObject = object;
        return this;
    }

    @Override
    public Object getWrappedInstance() {
        return wrappedObject;
    }

    @Override
    public Class<?> getWrappedClass() {
        return wrappedObject.getClass();
    }
}
