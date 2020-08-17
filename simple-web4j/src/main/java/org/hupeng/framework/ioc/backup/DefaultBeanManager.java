package org.hupeng.framework.ioc.backup;


import com.sun.istack.internal.NotNull;
import org.hupeng.framework.ioc.bean.BeanDefinition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author hupeng
 * @since 2019/3/10
 */
public class DefaultBeanManager implements BeanManager {

    private static Map<String, BeanDefinition<?>> beanMap;

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
    public <T> BeanDefinition<T> createBean(Class<T> beanClass) {
        //创建bean对象实现
        return beanConfigurator.doCreateBean(beanClass);
    }

    @Override
    public void addBean(final BeanDefinition<?> bean) {
        beanMap.put(bean.getName(),bean);
    }

    @Override
    public <T> BeanDefinition<T> getBean(Class<T> beanClass) {

        for (final Map.Entry<String, BeanDefinition<?>> entryMap: beanMap.entrySet()) {
            BeanDefinition<?> bean = entryMap.getValue();
            if (bean.getBeanClass().equals(beanClass)) {
                return (BeanDefinition<T>) bean;
            }
        }
        throw new RuntimeException("Not found bean [beanClass=" + beanClass.getName() + ']');
    }


    @NotNull
    @Override
    public Collection<BeanDefinition<?>> getBeans(Class<?> stereoType) {
        Collection<BeanDefinition<?>> beans = new ArrayList<>();
        AtomicBoolean isBean = new AtomicBoolean(false);
        for (final Map.Entry<String, BeanDefinition<?>> entryMap: beanMap.entrySet()) {
            BeanDefinition<?> bean = entryMap.getValue();
            Class<?> clazz = bean.getBeanClass();
            while(clazz != null && !isBean.get()){
                //是获取的子类
                if (clazz.equals(stereoType)) {
                    isBean.set(true);
                    break;
                }
                //是获取的接口实现类
                Class<?>[] interfaces = clazz.getInterfaces();
                if(interfaces != null){
                    for (Class<?> anInterface : interfaces) {
                        if (stereoType.equals(anInterface)) {
                            isBean.set(true);
                            break;
                        }
                    }
                }
                clazz=clazz.getSuperclass();
            }
            //是则加入此bean
            if(isBean.compareAndSet(true,false)){
                beans.add(bean);
            }
        }
        return beans;
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
