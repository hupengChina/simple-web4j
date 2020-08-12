package org.hupeng.framework.web.handler;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.web.server.http.WebRequest;

public interface HandlerMapping {

    @Nullable
    Object getHandler(WebRequest request);

}
