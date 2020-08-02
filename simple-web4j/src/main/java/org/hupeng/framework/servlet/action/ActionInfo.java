package org.hupeng.framework.servlet.action;

import java.lang.reflect.Method;

public class ActionInfo {

    private Class<?> controllerClass;

    private Method method;

    public ActionInfo(Class<?> controllerClass, Method method) {
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
