package org.hupeng.framework.web.server.netty;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.context.ConfigurableApplicationContext;
import org.hupeng.framework.context.bean.BeanDefinition;
import org.hupeng.framework.context.bean.DefaultBeanDefinition;
import org.hupeng.framework.context.support.ApplicationContextAware;
import org.hupeng.framework.web.config.WebConfigurer;
import org.hupeng.framework.web.handler.*;
import org.hupeng.framework.web.registry.HandlerRegistry;
import org.hupeng.framework.web.registry.RequestMappingHandlerRegistry;
import org.hupeng.framework.web.registry.ResourceHandlerRegistry;
import org.hupeng.framework.web.render.Renderer;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : hupeng
 * @date : 2020/8/26
 */
public class DispatcherHandler implements ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(DispatcherHandler.class);

    private static final List<HandlerMapping> handlerMappings = new ArrayList<HandlerMapping>();

    private static final List<HandlerAdapter> handlerAdapters = new ArrayList<HandlerAdapter>();

    public static void addHandlerMapping(HandlerMapping handlerMapping){
        handlerMappings.add(handlerMapping);
    }

    public static void addHandlerAdapter(HandlerAdapter handlerAdapter){
        handlerAdapters.add(handlerAdapter);
    }

    @Nullable
    private Object getHandler(WebRequest request){
        for (HandlerMapping handlerMapping : handlerMappings) {
            Object handler = handlerMapping.getHandler(request);
            if(handler != null){
                return handler;
            }
        }
        return null;
    }

    @Nullable
    private HandlerAdapter getHandlerAdapter(Object handler){
        for (HandlerAdapter handlerAdapter:handlerAdapters) {
            if(handlerAdapter.supports(handler)){
                return handlerAdapter;
            }
        }
        return null;
    }

    public void doService(WebRequest webRequest, WebResponse webResponse){

        Object handler = getHandler(webRequest);

        if(handler == null){
            webResponse.sendError(404);
            return;
        }

        HandlerAdapter handlerAdapter = getHandlerAdapter(handler);

        HandleResult result = handlerAdapter.handle(webRequest,webResponse,handler);

        Renderer renderer = result.getRenderer();

        renderer.render(webRequest,webResponse,result);

    }

    @Override
    public void setApplicationContext(ConfigurableApplicationContext context) {
        initStrategies(context);
    }

    protected void initStrategies(ConfigurableApplicationContext context) {
        initializer(context);

        configureResourceHandler(context);
        configureRequestMappingHandler(context);

        configureHandlerMapping(context.getBeansOfType(HandlerRegistry.class));
        configureHandlerAdapter(context.getBeansOfType(HandlerAdapter.class));
    }

    protected void initializer(ConfigurableApplicationContext context){
        BeanDefinition resourceHandlerRegistryBD = new DefaultBeanDefinition<>(ResourceHandlerRegistry.class);
        BeanDefinition requestMappingHandlerRegistryBD = new DefaultBeanDefinition<>(RequestMappingHandlerRegistry.class);
        context.getBeanFactory().registerBeanDefinition(resourceHandlerRegistryBD.getName(),resourceHandlerRegistryBD);
        context.getBeanFactory().registerBeanDefinition(requestMappingHandlerRegistryBD.getName(),requestMappingHandlerRegistryBD);

        BeanDefinition resourceHandlerAdapterBD = new DefaultBeanDefinition<>(ResourceHandlerAdapter.class);
        BeanDefinition requestMappingHandlerAdapterBD = new DefaultBeanDefinition<>(RequestMappingHandlerAdapter.class);
        context.getBeanFactory().registerBeanDefinition(resourceHandlerAdapterBD.getName(),resourceHandlerAdapterBD);
        context.getBeanFactory().registerBeanDefinition(requestMappingHandlerAdapterBD.getName(),requestMappingHandlerAdapterBD);

        BeanDefinition requestMappingHandlerMappingBD = new DefaultBeanDefinition<>(RequestMappingHandlerMapping.class);
        context.getBeanFactory().registerBeanDefinition(requestMappingHandlerMappingBD.getName(),requestMappingHandlerMappingBD);
        ((DefaultBeanDefinition) requestMappingHandlerMappingBD).setSynthetic(false);
        //test
        addHandlerMapping((HandlerMapping) context.getBean(requestMappingHandlerMappingBD.getName()));
    }


    protected void configureRequestMappingHandler(ConfigurableApplicationContext context) {
        final RequestMappingHandlerRegistry registry = context.getBean(RequestMappingHandlerRegistry.class);
        if (registry != null) {

        }
    }

    protected void configureResourceHandler(ConfigurableApplicationContext context) {
        final ResourceHandlerRegistry registry = context.getBean(ResourceHandlerRegistry.class);
        if (registry != null) {
            Map<String, WebConfigurer> webConfigurers = context.getBeansOfType(WebConfigurer.class);
            if(webConfigurers != null){
                webConfigurers.forEach((name,webConfigurer) -> {webConfigurer.addResourceHandlers(registry);});
            }
        }
    }

    protected void configureHandlerAdapter(Map<String,HandlerAdapter> handlerAdapters) {
        handlerAdapters.forEach((name,handlerAdapter) -> {
            addHandlerAdapter(handlerAdapter);
        });
    }

    protected void configureHandlerMapping(Map<String,HandlerRegistry> handlerRegistries) {
        handlerRegistries.forEach((name,handlerRegistry)->{
            HandlerMapping handlerMapping = handlerRegistry.getHandlerMapping();
            if(handlerMapping != null) {
                addHandlerMapping(handlerMapping);
            }
        });
    }
}
