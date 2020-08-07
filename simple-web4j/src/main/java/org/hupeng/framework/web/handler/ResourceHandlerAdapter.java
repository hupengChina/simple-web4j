package org.hupeng.framework.web.handler;

import org.hupeng.framework.web.render.StaticResourceRenderer;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

/**
 * 静态资源处理HandlerAdapter
 * @author hupeng
 * @since 2018/6/24
 */
public class ResourceHandlerAdapter implements HandlerAdapter{

    @Override
    public boolean supports(Object handler) {
        return handler instanceof ResourceHandler;
    }

    @Override
    public HandleResult handle(WebRequest request, WebResponse response, Object handler) {
        ResourceHandler resourceHandler = (ResourceHandler)handler;
        resourceHandler.handleRequest(request,response);
        HandleResult handleResult = new HandleResult();
        handleResult.setRenderer(new StaticResourceRenderer());
        return handleResult;
    }
}
