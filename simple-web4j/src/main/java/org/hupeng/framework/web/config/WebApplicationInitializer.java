package org.hupeng.framework.web.config;

import org.hupeng.framework.context.AnnotationConfigApplicationContext;

public interface WebApplicationInitializer {

    void onStartup(AnnotationConfigApplicationContext applicationContext);
}
