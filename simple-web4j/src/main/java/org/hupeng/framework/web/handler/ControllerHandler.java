package org.hupeng.framework.web.handler;

import java.lang.reflect.Method;

public class ControllerHandler {

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
}
