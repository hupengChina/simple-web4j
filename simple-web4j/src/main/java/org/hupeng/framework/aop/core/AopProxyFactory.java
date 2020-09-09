package org.hupeng.framework.aop.core;


import org.hupeng.framework.aop.support.AdvisedSupport;

public interface AopProxyFactory {

    AopProxy createAopProxy(AdvisedSupport config);

}
