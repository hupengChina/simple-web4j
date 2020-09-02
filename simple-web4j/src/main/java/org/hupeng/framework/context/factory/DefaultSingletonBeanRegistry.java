package org.hupeng.framework.context.factory;

import com.sun.istack.internal.Nullable;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : hupeng
 * @date : 2020/8/13
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    //beanName-完全的object （一级缓存）
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    //beanName-提前曝光的（未填充属性初始化）object（二级缓存）
    private final Map<String, Object> earlySingletonObjects = new HashMap<>();

    //beanName-object工厂（三级缓存）
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>();

    private final Set<String> registeredSingletons = new LinkedHashSet<>();

    //当前正在创建的单例beanName集合
    private final Set<String> singletonsCurrentlyInCreation = Collections.newSetFromMap(new ConcurrentHashMap<>());

    //key-key依赖的bean集合
    private final Map<String, Set<String>> dependentBeanMap = new ConcurrentHashMap<>();

    //key-依赖key的bean集合
    private final Map<String, Set<String>> dependenciesForBeanMap = new ConcurrentHashMap<>();

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
            singletonObjects.put(beanName, singletonObject);
    }

    @Override
    @Nullable
    public Object getSingleton(String beanName) {
        return getSingleton(beanName,true);
    }

    public Object getSingleton(String beanName, boolean allowEarlyReference) {
        Object singletonObject = this.singletonObjects.get(beanName);
        if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
            synchronized (this.singletonObjects) {
                singletonObject = this.earlySingletonObjects.get(beanName);
                if (singletonObject == null && allowEarlyReference) {
                    ObjectFactory<?> singletonFactory = this.singletonFactories.get(beanName);
                    //从三级缓存工厂获取结果（调用AbstractAutowireCapableBeanFactory.getEarlyBeanReference）移入二级缓存
                    if (singletonFactory != null) {
                        singletonObject = singletonFactory.getObject();
                        this.earlySingletonObjects.put(beanName, singletonObject);
                        this.singletonFactories.remove(beanName);
                    }
                }
            }
        }
        return singletonObject;
    }

    @Override
    public boolean containsSingleton(String beanName) {
        return singletonObjects.containsKey(beanName);
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
        synchronized (this.singletonObjects) {
            if (!this.singletonObjects.containsKey(beanName)) {
                this.singletonFactories.put(beanName, singletonFactory);
                this.earlySingletonObjects.remove(beanName);
                this.registeredSingletons.add(beanName);
            }
        }
    }

    public void registerDependentBean(String beanName, String dependentBeanName) {
        synchronized (this.dependentBeanMap) {
            Set<String> dependentBeans = this.dependentBeanMap.computeIfAbsent(beanName, k -> new LinkedHashSet<>(8));
            if (!dependentBeans.add(dependentBeanName)) {
                return;
            }
        }
        synchronized (this.dependenciesForBeanMap) {
            Set<String> dependenciesForBean = this.dependenciesForBeanMap.computeIfAbsent(beanName, k -> new LinkedHashSet<>(8));
            dependenciesForBean.add(beanName);
        }
    }

    protected boolean isDependent(String beanName, String dependentBeanName) {
        synchronized (this.dependentBeanMap) {
            return isDependent(beanName, dependentBeanName, null);
        }
    }

    private boolean isDependent(String beanName, String dependentBeanName, @Nullable Set<String> alreadySeen) {
        if (alreadySeen != null && alreadySeen.contains(beanName)) {
            return false;
        }
        Set<String> dependentBeans = this.dependentBeanMap.get(beanName);
        if (dependentBeans == null) {
            return false;
        }
        if (dependentBeans.contains(dependentBeanName)) {
            return true;
        }
        for (String transitiveDependency : dependentBeans) {
            if (alreadySeen == null) {
                alreadySeen = new HashSet<>();
            }
            alreadySeen.add(beanName);
            if (isDependent(transitiveDependency, dependentBeanName, alreadySeen)) {
                return true;
            }
        }
        return false;
    }

    protected boolean hasDependentBean(String beanName) {
        return this.dependentBeanMap.containsKey(beanName);
    }

    public Set<String> getDependentBeans(String beanName) {
        return this.dependentBeanMap.get(beanName);
    }

    public Object getSingleton(String beanName, ObjectFactory<?> singletonFactory) {
        synchronized (this.singletonObjects) {
            Object singletonObject = this.singletonObjects.get(beanName);
            if (singletonObject == null) {
                beforeSingletonCreation(beanName);//标记开始创建单例
                try {
                    singletonObject = singletonFactory.getObject();
                    addSingleton(beanName, singletonObject);
                } finally {
                    afterSingletonCreation(beanName);//去除标记开始创建单例，结束创建
                }

            }
            return singletonObject;
        }
    }

    protected void beforeSingletonCreation(String beanName) {
        if (!this.singletonsCurrentlyInCreation.add(beanName)) {
            throw new IllegalStateException("Singleton '" + beanName + "' currently in creation");
        }
    }

    protected void afterSingletonCreation(String beanName) {
        if (!this.singletonsCurrentlyInCreation.remove(beanName)) {
            throw new IllegalStateException("Singleton '" + beanName + "' isn't currently in creation");
        }
    }

    public boolean isSingletonCurrentlyInCreation(String beanName) {
        return this.singletonsCurrentlyInCreation.contains(beanName);
    }


    protected void addSingleton(String beanName, Object singletonObject) {
        synchronized (this.singletonObjects) {
            this.singletonObjects.put(beanName, singletonObject);
            this.singletonFactories.remove(beanName);
            this.earlySingletonObjects.remove(beanName);
            this.registeredSingletons.add(beanName);
        }
    }

}
