package org.hupeng.framework.web.handler;

import org.hupeng.framework.ioc.Annotated.RequestMapping;
import org.hupeng.framework.ioc.Annotated.Controller;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HandlerMapping {

    private final static Map<String, Handler> handlerReferences = new ConcurrentHashMap<>();

    public static void init(Collection<Class<?>> classes){
        for (Class clazz: classes) {
            if(clazz.isAnnotationPresent(Controller.class)){
                String className = clazz.getSimpleName();
                Method[] methods = clazz.getMethods();
                for (Method method: methods) {
                    RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                    if(requestMapping != null){
                        String requestPath = requestMapping.value();
                        Handler handler = new Handler(clazz,method);
                        handlerReferences.put(requestPath, handler);
                    }
                }

            }
        }
    }

    public static Handler getHandler(String requestPath) {
        return handlerReferences.get(requestPath);
    }
}
