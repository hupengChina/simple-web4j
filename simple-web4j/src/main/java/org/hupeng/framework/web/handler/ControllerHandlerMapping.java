package org.hupeng.framework.web.handler;

import org.hupeng.framework.ioc.Annotated.RequestMapping;
import org.hupeng.framework.ioc.Annotated.Controller;
import org.hupeng.framework.ioc.SingletonWebApplicationContext;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ControllerHandlerMapping implements HandlerMapping {

    private final static Map<String, ControllerHandler> handlerReferences = new ConcurrentHashMap<>();

    static {
        Collection<Class<?>> classes = SingletonWebApplicationContext.getInstance().getClasses();
        for (Class clazz: classes) {
            if(clazz.isAnnotationPresent(Controller.class)){
                String className = clazz.getSimpleName();
                Method[] methods = clazz.getMethods();
                for (Method method: methods) {
                    RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                    if(requestMapping != null){
                        String requestPath = requestMapping.value();
                        ControllerHandler controllerHandler = new ControllerHandler(clazz,method);
                        handlerReferences.put(requestPath, controllerHandler);
                    }
                }
            }
        }
    }

    @Override
    public Object getHandler(String requestPath) {
        return handlerReferences.get(requestPath);
    }

}
