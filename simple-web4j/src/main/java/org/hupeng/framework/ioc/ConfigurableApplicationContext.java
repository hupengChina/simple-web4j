package org.hupeng.framework.ioc;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.ioc.factory.DefaultBeanFactory;

public interface ConfigurableApplicationContext extends ApplicationContext {

    void refresh();

    DefaultBeanFactory getBeanFactory();

    void setConfigLocation(String configLocation);

    void setConfigLocations(String... configLocations);

    @Nullable
    String[] getConfigLocations();

}
