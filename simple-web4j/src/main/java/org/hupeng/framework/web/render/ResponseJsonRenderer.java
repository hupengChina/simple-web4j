package org.hupeng.framework.web.render;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.hupeng.framework.util.JsonUtil;
import org.hupeng.framework.web.handler.HandleResult;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

/**
 * @author : hupeng
 * @date : 2020/8/4 16:29
 */
public class ResponseJsonRenderer implements Renderer{

    @Override
    public void render(WebRequest request, WebResponse response, HandleResult handleResult) {
        FullHttpResponse resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.copiedBuffer(JsonUtil.toJson(handleResult.getResult()), CharsetUtil.UTF_8));
        resp.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        ChannelHandlerContext ctx = response.getCtx();
        ctx.writeAndFlush(resp).addListener(ChannelFutureListener.CLOSE);
    }

}
