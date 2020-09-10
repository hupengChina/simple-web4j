package org.hupeng.framework.aop;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.aop.core.Advisor;
import org.hupeng.framework.aop.core.TargetSource;
import org.hupeng.framework.context.factory.BeanFactory;
import org.hupeng.framework.context.factory.BeanFactoryAware;
import org.hupeng.framework.context.factory.config.EarlyReferenceInstantiationAwareBeanPostProcessor;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractAutoProxyCreator implements EarlyReferenceInstantiationAwareBeanPostProcessor, BeanFactoryAware {

    @Nullable
    private BeanFactory beanFactory;

    private final Map<Object, Object> earlyProxyReferences = new ConcurrentHashMap<>();

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Nullable
    protected BeanFactory getBeanFactory() {
        return this.beanFactory;
    }

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) {
        earlyProxyReferences.put(beanName,bean);
        return wrapIfNecessary(bean,beanName);
    }

    @Override
    public Object postProcessAfterInitialization(@Nullable Object bean, String beanName) {
        if (bean != null) {
            if (this.earlyProxyReferences.remove(beanName) != bean) {
                return wrapIfNecessary(bean, beanName);
            }
        }
        return bean;
    }

    protected Object wrapIfNecessary(Object bean, String beanName) {

        Collection advisors = getAdvisorsForBean(bean.getClass(), beanName, null);
        if (advisors != null) {
            Object proxy = createProxy(advisors, new SingletonTargetSource(bean));
            return proxy;
        }

        return bean;
    }

    protected Object createProxy(Collection<Advisor> advisors, TargetSource targetSource) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTargetSource(targetSource);
        advisors.forEach(advisor -> {
            proxyFactory.addAdvisor(advisor);
        });
        return proxyFactory.getProxy();
    }

    @Nullable
    protected abstract Collection<Advisor> getAdvisorsForBean(Class<?> beanClass, String beanName,
                                                               @Nullable TargetSource customTargetSource);

}
