package org.hupeng.framework.ioc.backup;

import com.sun.istack.internal.NotNull;
import org.hupeng.framework.ioc.bean.BeanDefinition;
import org.hupeng.framework.ioc.support.WebApplicationContext;
import org.hupeng.framework.util.ReflectionUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author: hupeng
 * @since: 2019/3/17 22:21
 */
public class SingletonWebApplicationContext implements WebApplicationContext {

    private DefaultClassScan defaultClassScan = new DefaultClassScan();

    private Map<BeanDefinition<?>, Object> beanReferences = new ConcurrentHashMap<>();

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
            createBean(clazz);
        }
        isInit.set(true);
    }


    @Override
    public Collection<Class<?>> getClasses() {
       return beanClasses;
    }


    public Object getBean(String name) {
        return null;
    }

    @Override
    public void createBean(Class clazz) {
        beanManager.createBean(clazz);
    }

    /**
     * 获取实例
     * @param beanClass
     * @param <T>
     * @return
     */
    @Override
    public <T> T getBean(final Class<T> beanClass) {
        BeanDefinition<T> bean = beanManager.getBean(beanClass);
        //获取时进行实例化
        if(beanReferences.get(bean) == null){
            beanReferences.put(bean, ReflectionUtil.newInstance(beanClass));
        }
        return (T) beanReferences.get(bean);
    }

    @NotNull
    @Override
    public <T> List<T> getBeans(Class<T> requiredType) {
        List<T> beanObjects = new ArrayList<>();
        Collection<BeanDefinition<?>> beans = beanManager.getBeans(requiredType);
        beans.forEach(bean -> {
            T o = (T) getBean(bean.getBeanClass());
            beanObjects.add(o);
        });
        return beanObjects;
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
