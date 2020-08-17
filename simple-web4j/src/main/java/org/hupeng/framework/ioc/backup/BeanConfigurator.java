package org.hupeng.framework.ioc.backup;

import org.hupeng.framework.ioc.backup.BeanManager;
import org.hupeng.framework.ioc.bean.BeanDefinition;
import org.hupeng.framework.ioc.bean.DefaultBeanDefinition;

/**
 * @Author: hupeng
 * @since: 2019/3/17 20:24
 */
public class BeanConfigurator {

    private BeanManager beanManager;

    BeanConfigurator(BeanManager beanManager){
        this.beanManager = beanManager;
    }

    /**
     * 根据class对象生成bean
     * @param beanClass
     * @param <T>
     * @return
     */
    public <T> BeanDefinition<T> doCreateBean(Class<T> beanClass) {
        BeanDefinition<T> bean = new DefaultBeanDefinition(beanClass);
        //将生成的bean对象交由beanManager管理
        beanManager.addBean(bean);
        return bean;
    }

}
