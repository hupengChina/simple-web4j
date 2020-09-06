package org.hupeng.framework.aop;

import org.hupeng.framework.context.factory.BeanFactory;

public class AbstractAdvisorAutoProxyCreator extends AbstractAutoProxyCreator  {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        super.setBeanFactory(beanFactory);
    }
}
