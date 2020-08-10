package org.hupeng.framework.web;

import org.hupeng.framework.ioc.support.WebApplicationContext;
import org.hupeng.framework.web.handler.*;
import org.hupeng.framework.web.registry.ResourceHandlerRegistry;
import org.hupeng.framework.web.render.Renderer;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : hupeng
 * @date : 2020/8/3 11:31
 */
public class Dispatcher {
    
    private static final Logger log = LoggerFactory.getLogger(Dispatcher.class);

    private static final List<HandlerMapping> handlerMappings = new ArrayList<HandlerMapping>();

    private static final List<HandlerAdapter> handlerAdapters = new ArrayList<HandlerAdapter>();

    public static void init(WebApplicationContext applicationContext){
        initHandlerMappings(applicationContext);
        initHandlerAdapters(applicationContext);
    }

    public static void initHandlerMappings(WebApplicationContext applicationContext){
        addHandlerMapping(new RequestMappingHandlerMapping());
    }

    public static void addHandlerMapping(HandlerMapping handlerMapping){
        handlerMappings.add(handlerMapping);
    }

    public static void initHandlerAdapters(WebApplicationContext applicationContext){
        handlerAdapters.add(new ResourceHandlerAdapter());
        handlerAdapters.add(new RequestMappingHandlerAdapter());
    }

    private static Object getHandler(String requestPath){
        for (HandlerMapping handlerMapping:handlerMappings) {
            Object handler = handlerMapping.getHandler(requestPath);
            if(handler != null){
                return handler;
            }
        }
        return null;
    }

    private static HandlerAdapter getHandlerAdapter(Object handler){
        for (HandlerAdapter handlerAdapter:handlerAdapters) {
            if(handlerAdapter.supports(handler)){
                return handlerAdapter;
            }
        }
        return null;
    }


    public static void doService(WebRequest webRequest, WebResponse webResponse){

        Object handler = getHandler(webRequest.getFullHttpRequest().uri());

        if(handler == null){
            webResponse.sendError(404);
            return;
        }

        HandlerAdapter handlerAdapter = getHandlerAdapter(handler);

        HandleResult result = handlerAdapter.handle(webRequest,webResponse,handler);

        Renderer renderer = result.getRenderer();

        renderer.render(webRequest,webResponse,result);

    }

}
