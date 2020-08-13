package org.hupeng.framework.web.handler;

import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

public interface HttpRequestHandler extends Handler{

    void handleRequest(WebRequest request, WebResponse response);
}
