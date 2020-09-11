package org.hupeng.framework.aop.proxy;

import org.hupeng.framework.aop.Advisor;
import org.hupeng.framework.aop.TargetSource;
import org.hupeng.framework.context.factory.BeanFactory;

import java.util.Collection;
import java.util.List;

public abstract class AbstractAdvisorAutoProxyCreator extends AbstractAutoProxyCreator  {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        super.setBeanFactory(beanFactory);
    }

    @Override
    protected Collection<Advisor> getAdvisorsForBean(Class<?> beanClass, String beanName, TargetSource customTargetSource) {

        List<Advisor> advisors = findAdvisorBeans();

        return null;
    }

    protected abstract List<Advisor> findAdvisorBeans();

}
