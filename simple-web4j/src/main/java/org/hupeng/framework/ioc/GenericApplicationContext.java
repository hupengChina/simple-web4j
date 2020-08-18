package org.hupeng.framework.ioc;

import org.hupeng.framework.ioc.bean.BeanDefinition;
import org.hupeng.framework.ioc.factory.BeanDefinitionRegistry;
import org.hupeng.framework.ioc.factory.DefaultBeanFactory;

/**
 * @author : hupeng
 * @date : 2020/8/14
 */
public class GenericApplicationContext extends AbstractApplicationContext implements BeanDefinitionRegistry {

    DefaultBeanFactory beanFactory = new DefaultBeanFactory();

    @Override
    public final DefaultBeanFactory getBeanFactory() {
        return this.beanFactory;
    }


    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanFactory.registerBeanDefinition(beanName,beanDefinition);
    }

    @Override
    public void removeBeanDefinition(String beanName) {
        beanFactory.removeBeanDefinition(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return beanFactory.getBeanDefinition(beanName);
    }
}
