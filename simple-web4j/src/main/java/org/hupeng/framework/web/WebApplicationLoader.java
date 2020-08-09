package org.hupeng.framework.web;

import org.hupeng.framework.ioc.support.WebApplicationContext;
import org.hupeng.framework.web.config.WebApplicationInitializer;

import java.util.List;

/**
 * @author : hupeng
 * @date : 2020/8/7
 */
public class WebApplicationLoader implements WebApplicationInitializer {


    @Override
    public void onStartup(WebApplicationContext applicationContext) {

        initializerStartup(applicationContext);
    }



    public void initializerStartup(WebApplicationContext applicationContext){
        final List<WebApplicationInitializer> initializers = applicationContext.getBeans(WebApplicationInitializer.class);
        if(initializers != null){
            for (final WebApplicationInitializer initializer : initializers) {
                initializer.onStartup(applicationContext);
            }
        }
    }

}
