package org.hupeng.framework.ioc;

import org.hupeng.framework.ioc.bean.Bean;
import org.hupeng.framework.ioc.support.DefaultClassScan;
import org.hupeng.framework.ioc.support.WebApplicationContext;
import org.hupeng.framework.util.ReflectionUtil;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author: hupeng
 * @since: 2019/3/17 22:21
 */
public class SingletonWebApplicationContext implements WebApplicationContext {

    private DefaultClassScan defaultClassScan = new DefaultClassScan();

    private Map<Bean<?>, Object> beanReferences = new ConcurrentHashMap<>();

    private Collection<Class<?>> beanClasses = new HashSet<>();

    private BeanManager beanManager;

    public AtomicBoolean isInit = new AtomicBoolean(false);

    private SingletonWebApplicationContext() {
        beanManager = DefaultBeanManager.getInstance();
    }

    /**
     * 单例容器初始化
     * @param packagePath
     */
    public void init(String packagePath){
        beanClasses = defaultClassScan.scan(packagePath);
        for (Class clazz: beanClasses) {
            //根据包创建bean对象
            beanManager.createBean(clazz);
        }
        isInit.set(true);
    }


    @Override
    public Collection<Class<?>> getClasses() {
       return beanClasses;
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

    public static SingletonWebApplicationContext getInstance() {
        return SingletonInstanceHolder.instance;
    }

    private static final class SingletonInstanceHolder {

        private static SingletonWebApplicationContext instance = new SingletonWebApplicationContext();

        private SingletonInstanceHolder() {
        }
    }
}
