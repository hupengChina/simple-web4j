package org.hupeng.framework.web.handler;

import io.netty.handler.codec.http.HttpHeaderNames;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

import java.lang.reflect.Method;

public class ControllerHandler implements Handler,HttpRequestHandler{

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

    @Override
    public void handleRequest(WebRequest request, WebResponse response){
        response.setHeader("access-control-allow-origin", "*");
        response.setHeader("content-type", "application/json;charset=UTF-8");
    }
}
