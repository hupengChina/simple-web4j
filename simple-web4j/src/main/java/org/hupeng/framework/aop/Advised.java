package org.hupeng.framework.aop;

import java.util.Collection;

public interface Advised {

    Collection<Class<?>> getProxiedInterfaces();

    boolean isInterfaceProxied(Class<?> intf);

    void setTargetSource(TargetSource targetSource);

    TargetSource getTargetSource();

    void addAdvisor(Advisor advisor);

    Collection<Advisor> getAdvisors();

    boolean removeAdvisor(Advisor advisor);

}
