package org.hupeng.framework.web.handler;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.ioc.support.WebApplicationObjectSupport;
import org.hupeng.framework.web.server.http.WebRequest;

/**
 * @author : hupeng
 * @date : 2020/8/12
 */
public abstract class AbstractHandlerMapping extends WebApplicationObjectSupport implements HandlerMapping {

    @Override
    @Nullable
    public final Object getHandler(WebRequest request) {
        Object handler = getHandlerInternal(request);
        return handler;
    }

    protected abstract Object getHandlerInternal(WebRequest request);

}
