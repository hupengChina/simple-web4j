package org.hupeng.framework.web.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.hupeng.framework.util.JsonUtil;
import org.hupeng.framework.web.Dispatcher;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

/**
 * @author : hupeng
 * @date : 2020/8/3 15:42
 */
public class ServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        WebRequest webRequest = new WebRequest().setFullHttpRequest(request);
        WebResponse webResponse = new WebResponse().setCtx(ctx);
        Dispatcher.doService(webRequest,webResponse);
    }
}
