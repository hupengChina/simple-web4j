package org.hupeng.framework.ioc;


import org.hupeng.framework.ioc.bean.Bean;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * @author hupeng
 * @since 2019/3/10.
 */
public class DefaultBeanManager implements BeanManager {

    private static Set<Bean<?>> beans;

    /**
     * 实际创建(组装)bean对象
     */
    private static BeanConfigurator beanConfigurator;

    private DefaultBeanManager() {
        beans = new HashSet<>();
        beanConfigurator = new BeanConfigurator(this);
        beanConfigurator.doCreateBean(BeanManager.class);
    }

    public static BeanManager getInstance() {
        return BeanManagerHolder.instance;
    }

    @Override
    public <T> Bean<T> createBean(Class<T> beanClass) {
        //创建bean对象实现
        return beanConfigurator.doCreateBean(beanClass);
    }

    @Override
    public void addBean(final Bean<?> bean) {
        beans.add(bean);
    }

    @Override
    public <T> Bean<T> getBean(Class<T> beanClass) {
        for (final Bean<?> bean : beans) {
            if (bean.getBeanClass().equals(beanClass)) {
                return (Bean<T>) bean;
            }
        }
        throw new RuntimeException("Not found bean [beanClass=" + beanClass.getName() + ']');
    }

    @Override
    public Set<Bean<?>> getBeans(Class<? extends Annotation> stereoType) {
        return null;
    }

    /**
     * 单例
     */
    private static final class BeanManagerHolder {

        private static BeanManager instance = new DefaultBeanManager();

        private BeanManagerHolder() {
        }
    }
}
