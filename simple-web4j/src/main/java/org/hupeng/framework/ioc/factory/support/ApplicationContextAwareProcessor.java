package org.hupeng.framework.ioc.factory.support;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.ioc.ApplicationContextAware;
import org.hupeng.framework.ioc.Aware;
import org.hupeng.framework.ioc.factory.config.BeanPostProcessor;
import org.hupeng.framework.ioc.support.WebApplicationContext;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * @author : hupeng
 * @date : 2020/8/14
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final WebApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(WebApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    @Nullable
    public Object postProcessBeforeInitialization(final Object bean, String beanName) {

        invokeAwareInterfaces(bean);
        return bean;
    }

    private void invokeAwareInterfaces(Object bean) {
        if (bean instanceof Aware) {

            if (bean instanceof ApplicationContextAware) {
                ((ApplicationContextAware) bean).setApplicationContext(this.applicationContext);
            }
        }
    }

}
