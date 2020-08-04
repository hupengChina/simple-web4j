package org.hupeng.framework.web.handler;

import org.hupeng.framework.helper.StaticResources;
import org.hupeng.framework.web.render.StaticResourceRenderer;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

/**
 * 静态资源处理Handler
 * @author hupeng
 * @since 2018/6/24.
 */
public class StaticResourceHandlerAdapter implements HandlerAdapter{

    @Override
    public boolean supports(Object handler) {
        return handler instanceof StaticResourceHandler;
    }

    @Override
    public HandleResult handle(WebRequest request, WebResponse response, Object handler) {
        //请求作用域放入对应资源渲染器
        HandleResult handleResult = new HandleResult();
        handleResult.setRenderer(new StaticResourceRenderer());
        return handleResult;
    }
}
