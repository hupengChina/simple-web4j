package org.hupeng.framework.web.handler;

import org.apache.tika.Tika;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

/**
 * @author : hupeng
 * @date : 2020/8/4
 */
public class StaticResourceHandler implements Handler,HttpRequestHandler{

    private static final Tika TIKA = new Tika();

    @Override
    public void handleRequest(WebRequest request, WebResponse response){
        final String contentType = TIKA.detect(request.getFullHttpRequest().uri());
        response.setHeader("content-type", contentType);
    }
}
