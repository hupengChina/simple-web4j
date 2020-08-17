package org.hupeng.framework.ioc;

import com.sun.istack.internal.Nullable;

public interface ConfigurableApplicationContext extends ApplicationContext {

    void refresh();

    void setConfigLocation(String configLocation);

    void setConfigLocations(String... configLocations);

    @Nullable
    String[] getConfigLocations();
}
