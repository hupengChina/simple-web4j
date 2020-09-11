package org.hupeng.framework.aop.proxy;

import org.hupeng.framework.aop.support.AdvisedSupport;
import org.hupeng.framework.commons.util.ClassUtil;

import java.util.Collection;

/**
 * @author : hupeng
 * @date : 2020/9/10
 */
public class JdkDynamicAopProxy implements AopProxy {

    private final AdvisedSupport advised;

    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        return getProxy(ClassUtil.getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {

        Collection<Class<?>> proxiedInterfaces =  advised.getProxiedInterfaces();

        return null;
    }
}
