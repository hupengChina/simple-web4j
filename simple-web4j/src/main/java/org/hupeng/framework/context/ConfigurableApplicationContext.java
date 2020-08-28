package org.hupeng.framework.context;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.context.factory.DefaultBeanFactory;

public interface ConfigurableApplicationContext extends ApplicationContext {

    void setId(String id);

    void setParent(@Nullable ApplicationContext parent);

    void refresh();

    void addApplicationListener(ApplicationListener<?> listener);

    DefaultBeanFactory getBeanFactory();

}
