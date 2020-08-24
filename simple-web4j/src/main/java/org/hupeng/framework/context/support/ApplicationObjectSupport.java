package org.hupeng.framework.context.support;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.context.AbstractApplicationContext;
import org.hupeng.framework.context.ConfigurableApplicationContext;

/**
 * @author : hupeng
 * @date : 2020/8/13
 */
public class ApplicationObjectSupport implements ApplicationContextAware {

    @Nullable
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    protected void initApplicationContext(AbstractApplicationContext context) {
        initApplicationContext();
    }

    protected void initApplicationContext() {
    }

    @Nullable
    public final ConfigurableApplicationContext getApplicationContext() {
        return this.applicationContext;
    }

    protected final ConfigurableApplicationContext obtainApplicationContext() {
        ConfigurableApplicationContext applicationContext = getApplicationContext();
        return applicationContext;
    }
}
