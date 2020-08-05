package org.hupeng.framework.ioc;


import org.hupeng.framework.ioc.bean.Bean;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hupeng
 * @since 2019/3/10
 */
public class DefaultBeanManager implements BeanManager {

    private static Map<String, Bean<?>> beanMap;

    /**
     * 实际创建(组装)bean对象
     */
    private static BeanConfigurator beanConfigurator;

    private DefaultBeanManager() {
        beanMap = new ConcurrentHashMap<>();
        beanConfigurator = new BeanConfigurator(this);
        beanConfigurator.doCreateBean(DefaultBeanManager.class);
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
        beanMap.put(bean.getName(),bean);
    }

    @Override
    public <T> Bean<T> getBean(Class<T> beanClass) {

        for (final Map.Entry<String, Bean<?>> entryMap: beanMap.entrySet()) {
            Bean<?> bean = entryMap.getValue();
            if (bean.getBeanClass().equals(beanClass)) {
                return (Bean<T>) bean;
            }
        }
        throw new RuntimeException("Not found bean [beanClass=" + beanClass.getName() + ']');
    }


    @Override
    public Collection<Bean<?>> getBeans(Class<? extends Annotation> stereoType) {
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
