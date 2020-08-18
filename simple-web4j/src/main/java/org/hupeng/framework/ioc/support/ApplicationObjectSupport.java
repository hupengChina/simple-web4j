package org.hupeng.framework.ioc.support;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.ioc.AbstractApplicationContext;

/**
 * @author : hupeng
 * @date : 2020/8/13
 */
public class ApplicationObjectSupport implements ApplicationContextAware {

    @Nullable
    private AbstractApplicationContext applicationContext;

    @Override
    public void setApplicationContext(AbstractApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    protected void initApplicationContext(AbstractApplicationContext context) {
        initApplicationContext();
    }

    protected void initApplicationContext() {
    }

    @Nullable
    public final AbstractApplicationContext getApplicationContext() {
        return this.applicationContext;
    }

    protected final AbstractApplicationContext obtainApplicationContext() {
        AbstractApplicationContext applicationContext = getApplicationContext();
        return applicationContext;
    }
}
