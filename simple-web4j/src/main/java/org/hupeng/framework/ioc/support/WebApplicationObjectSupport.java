package org.hupeng.framework.ioc.support;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.ioc.ApplicationContextAware;

/**
 * @author : hupeng
 * @date : 2020/8/13
 */
public class WebApplicationObjectSupport implements ApplicationContextAware {

    @Nullable
    private WebApplicationContext applicationContext;

    @Override
    public void setApplicationContext(WebApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    protected void initApplicationContext(WebApplicationContext context) {
        initApplicationContext();
    }

    protected void initApplicationContext() {
    }

    @Nullable
    public final WebApplicationContext getApplicationContext() {
        return this.applicationContext;
    }

    protected final WebApplicationContext obtainApplicationContext() {
        WebApplicationContext applicationContext = getApplicationContext();
        return applicationContext;
    }
}
