package org.hupeng.framework.aop.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.hupeng.framework.aop.Advisor;
import org.hupeng.framework.aop.proxy.AbstractAdvisorAutoProxyCreator;
import org.hupeng.framework.context.factory.ListableBeanFactory;

import java.util.ArrayList;
import java.util.List;

public class AspectJAwareAdvisorAutoProxyCreator extends AbstractAdvisorAutoProxyCreator {

    @Override
    protected List<Advisor> findAdvisorBeans() {
        List<Advisor> advisors = new ArrayList<>();
        ListableBeanFactory beanFactory = (ListableBeanFactory) getBeanFactory();
        String[] advisorNames = beanFactory.getBeanNamesForAnnotation(Aspect.class);

        for (String advisorName: advisorNames){



        }
        return advisors;
    }

}
