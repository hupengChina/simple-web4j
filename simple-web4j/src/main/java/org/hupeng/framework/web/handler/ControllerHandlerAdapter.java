package org.hupeng.framework.web.handler;

import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.hupeng.framework.ioc.SingletonInstanceContext;
import org.hupeng.framework.util.JsonUtil;
import org.hupeng.framework.util.ReflectionUtil;
import org.hupeng.framework.web.render.ResponseJsonRenderer;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

import java.lang.reflect.Method;

/**
 * @author hupeng
 * @since 2018/7/8.
 */
public class ControllerHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return handler instanceof ControllerHandler;
    }

    @Override
    public HandleResult handle(WebRequest webRequest, WebResponse webResponse, Object handler) {
        ControllerHandler controllerHandler = (ControllerHandler)handler;
        HttpMethod method = webRequest.getFullHttpRequest().method();
        String content = webRequest.getFullHttpRequest().content().toString(CharsetUtil.UTF_8);

        Class controllerClass = controllerHandler.getControllerClass();
        Method actionMethod = controllerHandler.getMethod();
        Object controller = null;
        try {
            controller = SingletonInstanceContext.getInstance().get(controllerClass);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Object result = ReflectionUtil.invokeMethod(controller, actionMethod);
        HandleResult handleResult = new HandleResult();
        new ResponseJsonRenderer();
        handleResult.setResult(result);
        handleResult.setRenderer(new ResponseJsonRenderer());
        return handleResult;
    }
}
