package org.hupeng.framework.context.factory.support;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.context.AbstractApplicationContext;
import org.hupeng.framework.context.support.ApplicationContextAware;
import org.hupeng.framework.context.support.Aware;
import org.hupeng.framework.context.factory.config.BeanPostProcessor;

/**
 * @author : hupeng
 * @date : 2020/8/14
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final AbstractApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(AbstractApplicationContext applicationContext) {
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
