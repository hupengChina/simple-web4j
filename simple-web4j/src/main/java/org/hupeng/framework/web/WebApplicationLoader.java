package org.hupeng.framework.web;

import org.hupeng.framework.ioc.support.WebApplicationContext;
import org.hupeng.framework.web.config.WebApplicationInitializer;
import org.hupeng.framework.web.config.WebConfigurer;
import org.hupeng.framework.web.handler.HandlerMapping;
import org.hupeng.framework.web.registry.HandlerRegistry;
import org.hupeng.framework.web.registry.ResourceHandlerRegistry;

import java.util.List;

/**
 * @author : hupeng
 * @date : 2020/8/7
 */
public class WebApplicationLoader implements WebApplicationInitializer {


    @Override
    public void onStartup(WebApplicationContext context) {

        Dispatcher.init(context);

        configureResourceHandler(context);

        configureHandlerMapping(context.getBeans(HandlerRegistry.class));

        initializerStartup(context);
    }

    protected void configureResourceHandler(WebApplicationContext context) {
        final ResourceHandlerRegistry registry = context.getBean(ResourceHandlerRegistry.class);
        if (registry != null) {
            List<WebConfigurer> webConfigurers = context.getBeans(WebConfigurer.class);
            if(webConfigurers != null){
                webConfigurers.forEach(webConfigurer -> {webConfigurer.addResourceHandlers(registry);});
            }
        }
    }

    protected void configureHandlerMapping(List<HandlerRegistry> handlerRegistries) {
        handlerRegistries.forEach((handlerRegistry)->{
            HandlerMapping handlerMapping = handlerRegistry.getHandlerMapping();
            if(handlerMapping != null) {
                Dispatcher.addHandlerMapping(handlerMapping);
            }
        });
    }



    public void initializerStartup(WebApplicationContext context){
        final List<WebApplicationInitializer> initializers = context.getBeans(WebApplicationInitializer.class);
        if(initializers != null){
            for (final WebApplicationInitializer initializer : initializers) {
                initializer.onStartup(context);
            }
        }
    }

}