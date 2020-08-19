package org.hupeng.framework.context;

import org.hupeng.framework.context.bean.BeanDefinition;
import org.hupeng.framework.context.factory.BeanDefinitionRegistry;
import org.hupeng.framework.context.factory.DefaultBeanFactory;

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
