package org.hupeng.framework.aop.aspectj.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.hupeng.framework.aop.advice.AfterAdvice;

/**
 * @author : hupeng
 * @date : 2020/9/11
 */
public class AspectJAfterAdvice extends AbstractAspectJAdvice
        implements MethodInterceptor, AfterAdvice {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return null;
    }
}
