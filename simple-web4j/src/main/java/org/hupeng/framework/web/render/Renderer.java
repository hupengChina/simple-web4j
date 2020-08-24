package org.hupeng.framework.web.render;

import org.hupeng.framework.web.handler.HandleResult;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;
import org.hupeng.framework.web.server.netty.http.WebNettyRequest;
import org.hupeng.framework.web.server.netty.http.WebNettyResponse;

public interface Renderer {

    void render(WebRequest request, WebResponse response, HandleResult handleResult);

}
