package org.hupeng.framework.web.server.netty.http;

import io.netty.handler.codec.http.FullHttpRequest;
import org.hupeng.framework.web.server.http.WebRequest;

/**
 * @author : hupeng
 * @date : 2020/8/3
 */
public class WebNettyRequest implements WebRequest {

    FullHttpRequest fullHttpRequest;

    public WebNettyRequest setFullHttpRequest(FullHttpRequest fullHttpRequest){
        this.fullHttpRequest = fullHttpRequest;
        return this;
    }

    @Override
    public String uri() {
        return fullHttpRequest.uri();
    }

    @Override
    public String method() {
        return fullHttpRequest.method().name();
    }
}
