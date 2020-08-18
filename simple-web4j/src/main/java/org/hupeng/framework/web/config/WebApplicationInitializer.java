package org.hupeng.framework.web.config;

import org.hupeng.framework.ioc.DefaultApplicationContext;

public interface WebApplicationInitializer {

    void onStartup(DefaultApplicationContext applicationContext);
}
