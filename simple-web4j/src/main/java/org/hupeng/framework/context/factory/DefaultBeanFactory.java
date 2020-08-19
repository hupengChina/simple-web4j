package org.hupeng.framework.context.factory;

import org.hupeng.framework.context.bean.BeanDefinition;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author : hupeng
 * @date : 2020/8/13
 */
public class DefaultBeanFactory extends AbstractAutowireCapableBeanFactory implements ListableBeanFactory,BeanDefinitionRegistry{

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private final Collection<Class<?>> beanClasses = new HashSet<>();

    public Collection<Class<?>> getBeanClasses(){
        return beanClasses;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanClasses.add(beanDefinition.getBeanClass());
        this.beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public void removeBeanDefinition(String beanName) {
        this.beanDefinitionMap.remove(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return this.beanDefinitionMap.get(beanName);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return this.beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public int getBeanDefinitionCount() {
        return 0;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }

    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        return new String[0];
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        Map<String, T> beans = new LinkedHashMap<>();
        AtomicBoolean isBean = new AtomicBoolean(false);
        for (final Map.Entry<String, BeanDefinition> entryMap: beanDefinitionMap.entrySet()) {
            BeanDefinition<?> bean = entryMap.getValue();
            Class<?> clazz = bean.getBeanClass();
            while(clazz != null && !isBean.get()){
                //是获取的子类
                if (clazz.equals(type)) {
                    isBean.set(true);
                    break;
                }
                //是获取的接口实现类
                Class<?>[] interfaces = clazz.getInterfaces();
                if(interfaces != null){
                    for (Class<?> anInterface : interfaces) {
                        if (type.equals(anInterface)) {
                            isBean.set(true);
                            break;
                        }
                    }
                }
                clazz=clazz.getSuperclass();
            }
            //是则加入此bean
            if(isBean.compareAndSet(true,false) && containsBean(bean.getName())){
                T beanObject = (T) getBean(bean.getName());
                if(beanObject != null) {
                    beans.put(bean.getName(), beanObject);
                }
            }
        }
        return beans;
    }

    @Override
    public String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotationType) {
        return new String[0];
    }

    @Override
    public Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) {
        return null;
    }

    @Override
    public <A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType) {
        return null;
    }

}
