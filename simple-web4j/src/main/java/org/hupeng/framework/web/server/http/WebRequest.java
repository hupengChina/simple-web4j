package org.hupeng.framework.web.server.http;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author : hupeng
 * @date : 2020/8/3
 */
public class WebRequest {

    FullHttpRequest fullHttpRequest;

    public WebRequest setFullHttpRequest(FullHttpRequest fullHttpRequest){
        this.fullHttpRequest = fullHttpRequest;
        return this;
    }

    public FullHttpRequest getFullHttpRequest(){
        return fullHttpRequest;
    }
}
