package org.hupeng.framework.web.server.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.hupeng.framework.web.server.http.WebResponse;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : hupeng
 * @date : 2020/8/3
 */
public class WebNettyResponse implements WebResponse {

    private byte[] content;

    private int status = 200;

    private Map<String,String> headers = new ConcurrentHashMap();

    private boolean keepAlive = false;

    private ChannelHandlerContext ctx;


    public WebNettyResponse setContent(final byte[] content) {
        this.content = content;
        return this;
    }

    public WebNettyResponse setContent(final String content) {
        if(content != null){
            this.content = content.getBytes(CharsetUtil.UTF_8);
        }
        return this;
    }

    public WebNettyResponse setKeepAlive(final boolean keepAlive) {
        this.keepAlive = keepAlive;
        return this;
    }

    public WebNettyResponse setCtx(ChannelHandlerContext ctx){
        this.ctx = ctx;
        return this;
    }

    @Override
    public WebNettyResponse setHeader(final String name, final String value) {
        headers.put(name, value);
        return this;
    }

    public WebNettyResponse setStatus(final int status) {
        this.status = status;
        return this;
    }

    @Override
    public void sendError(final int status){
        setStatus(status);
        writeAndFlush();
    }

    @Override
    public void sendByte(final byte[] content){
        setContent(content);
        writeAndFlush();
    }

    @Override
    public void sendString(final String content){
        setContent(content);
        writeAndFlush();
    }

    public void writeAndFlush(){
        final ByteBuf contentBuf = null != content ? Unpooled.copiedBuffer(content) : Unpooled.EMPTY_BUFFER;
        FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.valueOf(status),contentBuf);
        headers.forEach((name, value) -> {
            fullHttpResponse.headers().set(name, value);
        });
        if (keepAlive) {
            fullHttpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            fullHttpResponse.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, fullHttpResponse.content().readableBytes());
            ctx.writeAndFlush(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
        }else{
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }
    }

}
