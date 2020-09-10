package org.hupeng.framework.aop;

import org.hupeng.framework.aop.core.Advisor;
import org.hupeng.framework.aop.core.TargetSource;
import org.hupeng.framework.context.factory.BeanFactory;

import java.util.Collection;

public class AbstractAdvisorAutoProxyCreator extends AbstractAutoProxyCreator  {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        super.setBeanFactory(beanFactory);
    }

    @Override
    protected Collection<Advisor> getAdvisorsForBean(Class<?> beanClass, String beanName, TargetSource customTargetSource) {
        return null;
    }

}
