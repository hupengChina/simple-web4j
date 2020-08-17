package org.hupeng.framework.ioc;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.ioc.factory.AutowireCapableBeanFactory;
import org.hupeng.framework.ioc.support.DefaultClassScan;

import java.util.Collection;

/**
 * @author : hupeng
 * @date : 2020/8/17
 */
public abstract class AbstractApplicationContext implements ConfigurableApplicationContext {

    @Nullable
    private String[] configLocations;

    @Override
    public void refresh(){
        AutowireCapableBeanFactory beanFactory = obtainFreshBeanFactory();

    }

    protected abstract void refreshBeanFactory();

    @Override
    public void setConfigLocation(String configLocation) {
        setConfigLocations(configLocation);
    }

    @Override
    public void setConfigLocations(String... configLocations) {
        if (configLocations != null) {
            this.configLocations = new String[configLocations.length];
            for (int i = 0; i < configLocations.length; i++) {
                this.configLocations[i] = configLocations[i].trim();
            }
        }
    }

    @Override
    public String[] getConfigLocations() {
        return this.configLocations;
    }

    protected AutowireCapableBeanFactory obtainFreshBeanFactory() {
        refreshBeanFactory();
        return getAutowireCapableBeanFactory();
    }
}
