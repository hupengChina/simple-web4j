package org.hupeng.framework.web.handler;

import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

public interface HandlerAdapter {

    void handle(WebRequest request, WebResponse response, Handler handler, HandlerAdapterController handlerAdapterController);

}
