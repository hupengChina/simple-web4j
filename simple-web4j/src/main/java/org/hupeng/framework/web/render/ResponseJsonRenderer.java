package org.hupeng.framework.web.render;

import org.hupeng.framework.common.util.JsonUtil;
import org.hupeng.framework.web.handler.HandleResult;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;
import org.hupeng.framework.web.server.netty.http.WebNettyRequest;
import org.hupeng.framework.web.server.netty.http.WebNettyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : hupeng
 * @date : 2020/8/4
 */
public class ResponseJsonRenderer implements Renderer{

    private static final Logger log = LoggerFactory.getLogger(ResponseJsonRenderer.class);

    @Override
    public void render(WebRequest request, WebResponse response, HandleResult handleResult) {
        response.sendString(JsonUtil.toJson(handleResult.getResult()));
    }

}
