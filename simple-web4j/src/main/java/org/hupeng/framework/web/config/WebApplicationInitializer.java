package org.hupeng.framework.web.config;

import org.hupeng.framework.ioc.DefaultApplicationContext;
import org.hupeng.framework.ioc.support.WebApplicationContext;

public interface WebApplicationInitializer {

    void onStartup(DefaultApplicationContext applicationContext);
}
