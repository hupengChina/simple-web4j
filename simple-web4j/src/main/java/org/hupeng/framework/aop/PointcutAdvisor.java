package org.hupeng.framework.aop;

import org.hupeng.framework.aop.pointcut.Pointcut;

public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();

}
