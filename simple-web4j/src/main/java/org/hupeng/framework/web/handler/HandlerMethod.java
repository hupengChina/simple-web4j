package org.hupeng.framework.web.handler;

import java.lang.reflect.Method;

/**
 * @author : hupeng
 * @date : 2020/8/12
 */
public class HandlerMethod {

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

}
