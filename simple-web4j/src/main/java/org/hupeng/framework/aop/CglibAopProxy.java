package org.hupeng.framework.aop;

import org.hupeng.framework.aop.core.AopProxy;

/**
 * @author : hupeng
 * @date : 2020/9/10
 */
public class CglibAopProxy implements AopProxy {


    @Override
    public Object getProxy() {


        return null;
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return getProxy();
    }
}
