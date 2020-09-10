package org.hupeng.framework.aop;

import org.hupeng.framework.aop.core.TargetSource;

/**
 * @author : hupeng
 * @date : 2020/9/10
 */
public class SingletonTargetSource implements TargetSource{

    private final Object target;

    public SingletonTargetSource(Object target) {
        this.target = target;
    }

}