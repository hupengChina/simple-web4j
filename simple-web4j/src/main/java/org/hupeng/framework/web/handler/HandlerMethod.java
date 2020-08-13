package org.hupeng.framework.web.handler;

import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

import java.lang.reflect.Method;

/**
 * @author : hupeng
 * @date : 2020/8/12
 */
public class HandlerMethod implements HttpRequestHandler{

    public Object getBean() {
        return bean;
    }

    public Class<?> getBeanType() {
        return beanType;
    }

    public Method getMethod() {
        return method;
    }

    private final Object bean;

    private final Class<?> beanType;

    private final Method method;

    public HandlerMethod(Object bean, Method method) {
        this.bean = bean;
        this.beanType = bean.getClass();
        this.method = method;
    }


    private HandlerMethod(HandlerMethod handlerMethod, Object handler) {
        this.bean = handler;
        this.beanType = handlerMethod.beanType;
        this.method = handlerMethod.method;
    }

    public HandlerMethod createWithResolvedBean() {
        Object handler = this.bean;
        return new HandlerMethod(this, handler);
    }

    @Override
    public void handleRequest(WebRequest request, WebResponse response){
        response.setHeader("access-control-allow-origin", "*");
        response.setHeader("content-type", "application/json;charset=UTF-8");
    }
}
