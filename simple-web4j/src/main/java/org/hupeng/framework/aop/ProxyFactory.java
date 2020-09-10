package org.hupeng.framework.aop;

import org.hupeng.framework.aop.support.ProxyCreatorSupport;

/**
 * @author : hupeng
 * @date : 2020/9/8
 */
public class ProxyFactory extends ProxyCreatorSupport {

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

}
