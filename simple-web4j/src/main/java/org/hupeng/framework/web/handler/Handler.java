package org.hupeng.framework.web.handler;

import java.lang.reflect.Method;

public class Handler {

    private Class<?> controllerClass;

    private Method method;

    public Handler(Class<?> controllerClass, Method method) {
        this.controllerClass = controllerClass;
        this.method = method;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getMethod() {
        return method;
    }
}
