package org.hupeng.framework.web.handler;

import org.hupeng.framework.context.Annotated.Controller;
import org.hupeng.framework.web.annotated.RequestMapping;
import org.hupeng.framework.web.annotated.RequestMappingInfo;
import org.hupeng.framework.web.server.http.WebRequest;

import java.lang.reflect.Method;

public class RequestMappingHandlerMapping extends AbstractHandlerMethodMapping<RequestMappingInfo> {

    @Override
    protected boolean isHandler(Class<?> beanType) {
        return beanType.isAnnotationPresent(Controller.class);
    }

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        if(requestMapping == null){
            return null;
        }
        RequestMappingInfo requestMappingInfo = new RequestMappingInfo();
        requestMappingInfo.setValue(requestMapping.value()).setMethod(requestMapping.method());
        return requestMappingInfo;
    }

    @Override
    protected boolean matchingMapping(RequestMappingInfo mapping, WebRequest request) {
        return mapping.matchingCondition(request);
    }

    @Override
    protected String getMappingPathPattern(RequestMappingInfo mapping) {
        return mapping.getValue();
    }

}
