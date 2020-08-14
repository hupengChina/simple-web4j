package org.hupeng.framework.ioc.factory;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.ioc.bean.BeanDefinition;

public interface AutowireCapableBeanFactory extends BeanFactory {

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName);

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName);
}
