package org.hupeng.framework.aop.aspectj.advice;

import org.hupeng.framework.aop.advice.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author : hupeng
 * @date : 2020/9/11
 */
public class AspectJMethodBeforeAdvice extends AbstractAspectJAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {

    }

}
