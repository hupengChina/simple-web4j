package org.hupeng.framework.aop.support;


import org.hupeng.framework.aop.core.Advised;
import org.hupeng.framework.aop.core.Advisor;
import org.hupeng.framework.aop.core.TargetSource;

import java.util.*;

/**
 * @author : hupeng
 * @date : 2020/9/8
 */
public class AdvisedSupport implements Advised {

    TargetSource targetSource;

    private List<Class<?>> interfaces = new ArrayList<>();

    private List<Advisor> advisors = new ArrayList<>();

    public void addInterface(Class<?> intf) {
        this.interfaces.add(intf);
    }

    public boolean removeInterface(Class<?> intf) {
        return this.interfaces.remove(intf);
    }

    @Override
    public Collection<Class<?>> getProxiedInterfaces() {
        return interfaces;
    }

    @Override
    public boolean isInterfaceProxied(Class<?> intf) {
        for (Class<?> proxyIntf : this.interfaces) {
            if (intf.isAssignableFrom(proxyIntf)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    @Override
    public TargetSource getTargetSource() {
        return targetSource;
    }


    @Override
    public Collection<Advisor> getAdvisors() {
        return advisors;
    }

    @Override
    public boolean removeAdvisor(Advisor advisor) {
        return this.advisors.remove(advisor);
    }

    @Override
    public void addAdvisor(Advisor advisor) {
        advisors.add(advisor);
    }

}
