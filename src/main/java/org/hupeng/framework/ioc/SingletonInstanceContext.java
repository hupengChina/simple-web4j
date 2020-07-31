package org.hupeng.framework.ioc;

import org.hupeng.framework.ioc.bean.Bean;
import org.hupeng.framework.ioc.bean.DefaultBean;
import org.hupeng.framework.ioc.bean.FieldBean;
import org.hupeng.framework.ioc.support.DefaultClassScan;
import org.hupeng.framework.util.ReflectionUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: hupeng
 * @since: 2019/3/17 22:21
 */
public class SingletonInstanceContext {

    private DefaultClassScan defaultClassScan = new DefaultClassScan();

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
        Collection<Class<?>> classes= defaultClassScan.scan(packagePath);
        for (Class clazz: classes) {
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
        if(beanReferences.get(bean) == null){



            beanReferences.put(bean, ReflectionUtil.newInstance(beanClass));
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
