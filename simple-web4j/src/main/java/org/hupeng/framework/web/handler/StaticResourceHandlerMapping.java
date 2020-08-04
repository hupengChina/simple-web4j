package org.hupeng.framework.web.handler;

import org.hupeng.framework.helper.StaticResources;

/**
 * @author : hupeng
 * @date : 2020/8/4 14:28
 */
public class StaticResourceHandlerMapping implements HandlerMapping {

    @Override
    public Object getHandler(String requestPath) {
        if(StaticResources.isStatic(requestPath)) {
            return new StaticResourceHandler();
        }
        return null;
    }
}
