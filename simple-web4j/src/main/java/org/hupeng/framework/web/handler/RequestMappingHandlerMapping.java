package org.hupeng.framework.web.handler;

import org.hupeng.framework.web.annotated.RequestMapping;
import org.hupeng.framework.web.annotated.Controller;
import org.hupeng.framework.ioc.SingletonWebApplicationContext;
import org.hupeng.framework.web.annotated.RequestMappingInfo;
import org.hupeng.framework.web.server.http.WebRequest;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RequestMappingHandlerMapping extends AbstractHandlerMethodMapping<RequestMappingInfo> {

    private final static Map<String, RequestMappingHandler> handlerReferences = new ConcurrentHashMap<>();

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
                        RequestMappingHandler RequestMappingHandler = new RequestMappingHandler(clazz,method);
                        handlerReferences.put(requestPath, RequestMappingHandler);
                    }
                }
            }
        }
    }

    @Override
    protected Object getHandlerInternal(WebRequest request) {
        return handlerReferences.get(request.getFullHttpRequest().uri());
    }

    @Override
    protected boolean matchingMapping(RequestMappingInfo mapping, WebRequest request) {
        return mapping.matchingCondition(request);
    }
}
