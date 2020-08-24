package org.hupeng.framework.web.handler;

import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;
import org.hupeng.framework.web.server.netty.http.WebNettyRequest;
import org.hupeng.framework.web.server.netty.http.WebNettyResponse;

public interface HandlerAdapter {

    boolean supports(Object handler);

    HandleResult handle(WebRequest request, WebResponse response, Object handler);

}
