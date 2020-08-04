package org.hupeng.framework.web.handler;

import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

public interface HandlerAdapter {

    boolean supports(Object handler);

    HandleResult handle(WebRequest request, WebResponse response, Object handler);

}
