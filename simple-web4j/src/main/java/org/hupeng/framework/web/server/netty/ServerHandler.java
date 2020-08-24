package org.hupeng.framework.web.server.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import org.hupeng.framework.web.Dispatcher;
import org.hupeng.framework.web.server.netty.http.WebNettyRequest;
import org.hupeng.framework.web.server.netty.http.WebNettyResponse;

/**
 * @author : hupeng
 * @date : 2020/8/3
 */
public class ServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request){
        WebNettyRequest webRequest = new WebNettyRequest()
                .setFullHttpRequest(request);
        WebNettyResponse webResponse = new WebNettyResponse()
                .setCtx(ctx)
                .setKeepAlive(HttpUtil.isKeepAlive(request));
        Dispatcher.doService(webRequest,webResponse);
    }

}
