package org.hupeng.framework.ioc;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.ioc.bean.InitializingBean;
import org.hupeng.framework.ioc.factory.AbstractBeanFactory;
import org.hupeng.framework.ioc.factory.AutowireCapableBeanFactory;
import org.hupeng.framework.ioc.factory.DefaultBeanFactory;
import org.hupeng.framework.ioc.support.DefaultClassScan;

/**
 * @author : hupeng
 * @date : 2020/8/14
 */
public abstract class AbstractRefreshApplicationContext extends AbstractApplicationContext implements InitializingBean {

    @Nullable
    private String[] configLocations;

    DefaultBeanFactory beanFactory = new DefaultBeanFactory();

    DefaultClassScan scan = new DefaultClassScan();

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

    @Override
    public AutowireCapableBeanFactory getAutowireCapableBeanFactory() {
        return beanFactory;
    }

    @Override
    protected void refreshBeanFactory() {

    }

    @Override
    public final DefaultBeanFactory getBeanFactory() {
        return this.beanFactory;
    }

    protected abstract void loadBeanDefinitions(DefaultBeanFactory beanFactory);

    @Override
    public void afterPropertiesSet() {
        refresh();
    }
}
