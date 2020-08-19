package org.hupeng.framework.web;

import org.hupeng.framework.context.DefaultApplicationContext;
import org.hupeng.framework.context.bean.BeanDefinition;
import org.hupeng.framework.context.bean.DefaultBeanDefinition;
import org.hupeng.framework.web.config.WebApplicationInitializer;
import org.hupeng.framework.web.config.WebConfigurer;
import org.hupeng.framework.web.handler.*;
import org.hupeng.framework.web.registry.HandlerRegistry;
import org.hupeng.framework.web.registry.RequestMappingHandlerRegistry;
import org.hupeng.framework.web.registry.ResourceHandlerRegistry;

import java.util.Map;

/**
 * @author : hupeng
 * @date : 2020/8/7
 */
public class WebApplicationLoader implements WebApplicationInitializer {


    @Override
    public void onStartup(DefaultApplicationContext context) {

        initializer(context);

        configureResourceHandler(context);
        configureRequestMappingHandler(context);

        configureHandlerMapping(context.getBeansOfType(HandlerRegistry.class));
        configureHandlerAdapter(context.getBeansOfType(HandlerAdapter.class));

        initializerStartup(context);
    }

    protected void initializer(DefaultApplicationContext context){
        BeanDefinition resourceHandlerRegistryBD = new DefaultBeanDefinition<>(ResourceHandlerRegistry.class);
        BeanDefinition requestMappingHandlerRegistryBD = new DefaultBeanDefinition<>(RequestMappingHandlerRegistry.class);
        context.registerBeanDefinition(resourceHandlerRegistryBD.getName(),resourceHandlerRegistryBD);
        context.registerBeanDefinition(requestMappingHandlerRegistryBD.getName(),requestMappingHandlerRegistryBD);

        BeanDefinition resourceHandlerAdapterBD = new DefaultBeanDefinition<>(ResourceHandlerAdapter.class);
        BeanDefinition requestMappingHandlerAdapterBD = new DefaultBeanDefinition<>(RequestMappingHandlerAdapter.class);
        context.registerBeanDefinition(resourceHandlerAdapterBD.getName(),resourceHandlerAdapterBD);
        context.registerBeanDefinition(requestMappingHandlerAdapterBD.getName(),requestMappingHandlerAdapterBD);

        BeanDefinition requestMappingHandlerMappingBD = new DefaultBeanDefinition<>(RequestMappingHandlerMapping.class);
        context.registerBeanDefinition(requestMappingHandlerMappingBD.getName(),requestMappingHandlerMappingBD);
        ((DefaultBeanDefinition) requestMappingHandlerMappingBD).setSynthetic(false);
        //test
        Dispatcher.addHandlerMapping((HandlerMapping) context.getBean(requestMappingHandlerMappingBD.getName()));
    }


    protected void configureRequestMappingHandler(DefaultApplicationContext context) {
        final RequestMappingHandlerRegistry registry = context.getBean(RequestMappingHandlerRegistry.class);
        if (registry != null) {

        }
    }

    protected void configureResourceHandler(DefaultApplicationContext context) {
        final ResourceHandlerRegistry registry = context.getBean(ResourceHandlerRegistry.class);
        if (registry != null) {
            Map<String,WebConfigurer> webConfigurers = context.getBeansOfType(WebConfigurer.class);
            if(webConfigurers != null){
                webConfigurers.forEach((name,webConfigurer) -> {webConfigurer.addResourceHandlers(registry);});
            }
        }
    }

    protected void configureHandlerAdapter(Map<String,HandlerAdapter> handlerAdapters) {
        handlerAdapters.forEach((name,handlerAdapter) -> {
            Dispatcher.addHandlerAdapter(handlerAdapter);
        });
    }

    protected void configureHandlerMapping(Map<String,HandlerRegistry> handlerRegistries) {
        handlerRegistries.forEach((name,handlerRegistry)->{
            HandlerMapping handlerMapping = handlerRegistry.getHandlerMapping();
            if(handlerMapping != null) {
                Dispatcher.addHandlerMapping(handlerMapping);
            }
        });
    }



    public void initializerStartup(DefaultApplicationContext context){
        final Map<String,WebApplicationInitializer> initializers = context.getBeansOfType(WebApplicationInitializer.class);
        if(initializers != null){
            initializers.forEach((name,initializer)->{
                initializer.onStartup(context);
            });
        }
    }

}
