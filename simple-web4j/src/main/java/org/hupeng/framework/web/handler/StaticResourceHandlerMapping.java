package org.hupeng.framework.web.handler;

import org.hupeng.framework.helper.StaticResources;

/**
 * @author : hupeng
 * @date : 2020/8/4
 */
public class StaticResourceHandlerMapping implements HandlerMapping {

    @Override
    public Object getHandler(String requestPath) {
        if(StaticResources.isStatic(requestPath)) {
            StaticResourceHandler staticResourceHandler = new StaticResourceHandler();
            return staticResourceHandler;
        }
        return null;
    }
}
