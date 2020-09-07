package org.hupeng.framework.aop.aspectj.support;

public interface Advisor {

    Advice EMPTY_ADVICE = new Advice() {};

    Advice getAdvice();

    boolean isPerInstance();

}
