package org.hupeng.framework.web;

import io.netty.handler.codec.http.HttpResponseStatus;
import org.hupeng.framework.web.handler.*;
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

    public static void init(){
        initHandlerMappings();
        initHandlerAdapters();
    }

    public static void initHandlerMappings(){
        handlerMappings.add(new StaticResourceHandlerMapping());
        handlerMappings.add(new ControllerHandlerMapping().init());
    }

    public static void initHandlerAdapters(){
        handlerAdapters.add(new StaticResourceHandlerAdapter());
        handlerAdapters.add(new ControllerHandlerAdapter());
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
            webResponse.sendError(HttpResponseStatus.NOT_FOUND);
            return;
        }

        HandlerAdapter handlerAdapter = getHandlerAdapter(handler);

        HandleResult result = handlerAdapter.handle(webRequest,webResponse,handler);

        Renderer renderer = result.getRenderer();

        renderer.render(webRequest,webResponse,result);

    }

}
