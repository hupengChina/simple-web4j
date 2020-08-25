package org.hupeng.framework.web;

import org.hupeng.framework.context.AnnotationConfigApplicationContext;
import org.hupeng.framework.context.bean.BeanDefinition;
import org.hupeng.framework.context.bean.DefaultBeanDefinition;
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
public class DispatcherInitializer {


    private final AnnotationConfigApplicationContext context;

    DispatcherInitializer(AnnotationConfigApplicationContext context){
        this.context = context;
    }

    public void initStrategies() {

        initializer();

        configureResourceHandler();
        configureRequestMappingHandler();

        configureHandlerMapping(context.getBeansOfType(HandlerRegistry.class));
        configureHandlerAdapter(context.getBeansOfType(HandlerAdapter.class));
    }

    protected void initializer(){
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


    protected void configureRequestMappingHandler() {
        final RequestMappingHandlerRegistry registry = context.getBean(RequestMappingHandlerRegistry.class);
        if (registry != null) {

        }
    }

    protected void configureResourceHandler() {
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

}
