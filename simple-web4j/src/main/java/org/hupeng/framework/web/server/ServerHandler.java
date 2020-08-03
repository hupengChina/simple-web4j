package org.hupeng.framework.web.server;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import org.hupeng.framework.web.Dispatcher;
import org.hupeng.framework.web.handler.*;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : hupeng
 * @date : 2020/8/3 15:42
 */
public class ServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        String uri = request.uri();

        Handler handler = HandlerMapping.getHandler(uri);
        if(handler != null){

            WebRequest webRequest = new WebRequest().setFullHttpRequest(request);
            WebResponse webResponse = new WebResponse();

            Dispatcher.doService(webRequest,webResponse,handler);

            FullHttpResponse response = webResponse.getFullHttpResponse();
            if(response != null){
                ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
            }

        }

    }
}
