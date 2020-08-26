package org.hupeng.framework.web.handler;

import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.hupeng.framework.context.support.ApplicationObjectSupport;
import org.hupeng.framework.common.util.ReflectionUtil;
import org.hupeng.framework.web.render.ResponseJsonRenderer;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;
import org.hupeng.framework.web.server.netty.http.WebNettyRequest;
import org.hupeng.framework.web.server.netty.http.WebNettyResponse;

import java.lang.reflect.Method;

/**
 * @author hupeng
 * @since 2018/7/8
 */
public class RequestMappingHandlerAdapter extends ApplicationObjectSupport implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return handler instanceof HandlerMethod;
    }

    @Override
    public HandleResult handle(WebRequest webRequest, WebResponse webResponse, Object handler) {
        HandleResult handleResult = new HandleResult();

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        String method = webRequest.method();

        handlerMethod.handleRequest(webRequest,webResponse);
        Class controllerClass = handlerMethod.getBeanType();
        Method actionMethod = handlerMethod.getMethod();

        Object controller = obtainApplicationContext().getBean(controllerClass);

        Object result = ReflectionUtil.invokeMethod(controller, actionMethod);
        handleResult.setResult(result);
        handleResult.setRenderer(new ResponseJsonRenderer());
        return handleResult;
    }
}
