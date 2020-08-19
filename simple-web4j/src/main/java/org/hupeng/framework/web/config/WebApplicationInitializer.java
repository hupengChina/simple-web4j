package org.hupeng.framework.web.config;

import org.hupeng.framework.context.DefaultApplicationContext;

public interface WebApplicationInitializer {

    void onStartup(DefaultApplicationContext applicationContext);
}
