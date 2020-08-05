package org.hupeng.framework.web.render;

import org.hupeng.framework.util.JsonUtil;
import org.hupeng.framework.web.handler.HandleResult;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

/**
 * @author : hupeng
 * @date : 2020/8/4
 */
public class ResponseJsonRenderer implements Renderer{

    @Override
    public void render(WebRequest request, WebResponse response, HandleResult handleResult) {
        response.writeAndFlush(JsonUtil.toJson(handleResult.getResult()));
    }

}
