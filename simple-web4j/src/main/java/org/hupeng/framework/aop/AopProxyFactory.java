package org.hupeng.framework.aop;

public interface AopProxyFactory {

    AopProxy createAopProxy(AdvisedSupport config);

}
