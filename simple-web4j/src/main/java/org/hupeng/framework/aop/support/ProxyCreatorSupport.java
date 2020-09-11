package org.hupeng.framework.aop.support;

import org.hupeng.framework.aop.proxy.AopProxy;
import org.hupeng.framework.aop.proxy.AopProxyFactory;

/**
 * @author : hupeng
 * @date : 2020/9/8
 */
public class ProxyCreatorSupport extends AdvisedSupport {

    private AopProxyFactory aopProxyFactory;

    public ProxyCreatorSupport() {}

    public void setAopProxyFactory(AopProxyFactory aopProxyFactory) {
        this.aopProxyFactory = aopProxyFactory;
    }

    public AopProxyFactory getAopProxyFactory() {
        return this.aopProxyFactory;
    }

    protected final synchronized AopProxy createAopProxy() {
        return getAopProxyFactory().createAopProxy(this);
    }

}
