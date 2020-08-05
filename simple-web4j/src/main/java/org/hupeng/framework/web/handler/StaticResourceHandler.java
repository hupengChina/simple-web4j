package org.hupeng.framework.web.handler;

import io.netty.handler.codec.http.*;
import org.apache.tika.Tika;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

import java.io.IOException;

/**
 * @author : hupeng
 * @date : 2020/8/4
 */
public class StaticResourceHandler {

    private static final Tika TIKA = new Tika();

    public void handleRequest(WebRequest request, WebResponse response){
        FullHttpResponse resp = response.getFullHttpResponse();
        final String contentType = TIKA.detect(request.getFullHttpRequest().uri());
        resp.headers().set(HttpHeaderNames.CONTENT_TYPE, contentType);
    }
}
