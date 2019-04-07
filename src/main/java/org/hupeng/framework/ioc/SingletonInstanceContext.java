package org.hupeng.framework.ioc;

import org.hupeng.framework.ioc.bean.Bean;
import org.hupeng.framework.util.ClassUtil;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: hupeng
 * @since: 2019/3/17 22:21
 */
public class SingletonInstanceContext {

    private Map<Bean<?>, Object> beanReferences;

    private BeanManager beanManager;

    private SingletonInstanceContext() {
        beanReferences = new ConcurrentHashMap<>();
        beanManager = DefaultBeanManager.getInstance();
    }

    /**
     * 单例容器初始化
     * @param packagePath
     */
    public void init(String packagePath){
        Set<Class<?>> classSet = ClassUtil.getClassSet(packagePath);
        for (Class clazz: classSet) {
            //根据包创建bean对象
            beanManager.createBean(clazz);
        }
    }

    /**
     * 获取实例
     * @param beanClass
     * @param <T>
     * @return
     */
    public <T> T get(final Class<T> beanClass) throws InstantiationException, IllegalAccessException {
        Bean<T> bean = beanManager.getBean(beanClass);
        //获取时进行实例化
        if(beanReferences.get(bean) != null){
            beanReferences.put(bean, bean.getInstance());
        }
        return (T) beanReferences.get(bean);
    }

    public static SingletonInstanceContext getInstance() {
        return SingletonInstanceHolder.instance;
    }

    private static final class SingletonInstanceHolder {

        private static SingletonInstanceContext instance = new SingletonInstanceContext();

        private SingletonInstanceHolder() {
        }
    }
}
