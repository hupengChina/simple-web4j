package org.hupeng.framework.web.render;

import org.hupeng.framework.web.handler.HandleResult;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

public interface Renderer {

    void render(WebRequest request, WebResponse response, HandleResult handleResult);

}
