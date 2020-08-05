package org.hupeng.framework.web.handler;

import io.netty.handler.codec.http.HttpHeaderNames;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

import java.lang.reflect.Method;

public class ControllerHandler implements Handler{

    private Class<?> controllerClass;

    private Method method;

    public ControllerHandler(Class<?> controllerClass, Method method) {
        this.controllerClass = controllerClass;
        this.method = method;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getMethod() {
        return method;
    }

    public void handleRequest(WebRequest request, WebResponse response){
        response.setHeader(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        response.setHeader(HttpHeaderNames.CONTENT_TYPE, "application/json;charset=UTF-8");
    }
}
