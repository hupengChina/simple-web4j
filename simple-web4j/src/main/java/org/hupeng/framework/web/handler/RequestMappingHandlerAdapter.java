package org.hupeng.framework.web.handler;

import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.hupeng.framework.ioc.backup.SingletonWebApplicationContext;
import org.hupeng.framework.util.ReflectionUtil;
import org.hupeng.framework.web.render.ResponseJsonRenderer;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

import java.lang.reflect.Method;

/**
 * @author hupeng
 * @since 2018/7/8
 */
public class RequestMappingHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return handler instanceof HandlerMethod;
    }

    @Override
    public HandleResult handle(WebRequest webRequest, WebResponse webResponse, Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        HttpMethod method = webRequest.getFullHttpRequest().method();
        String content = webRequest.getFullHttpRequest().content().toString(CharsetUtil.UTF_8);
        handlerMethod.handleRequest(webRequest,webResponse);
        Class controllerClass = handlerMethod.getBeanType();
        Method actionMethod = handlerMethod.getMethod();

        Object controller = SingletonWebApplicationContext.getInstance().getBean(controllerClass);

        Object result = ReflectionUtil.invokeMethod(controller, actionMethod);
        HandleResult handleResult = new HandleResult();
        handleResult.setResult(result);
        handleResult.setRenderer(new ResponseJsonRenderer());
        return handleResult;
    }
}
