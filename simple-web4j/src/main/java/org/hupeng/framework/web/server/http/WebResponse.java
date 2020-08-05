package org.hupeng.framework.web.server.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.AsciiString;
import io.netty.util.CharsetUtil;

/**
 * @author : hupeng
 * @date : 2020/8/3
 */
public class WebResponse {

    private FullHttpResponse fullHttpResponse;

    private byte[] content;

    private boolean keepAlive = false;

    private ChannelHandlerContext ctx;

    public WebResponse setFullHttpResponse(FullHttpResponse fullHttpResponse){
        this.fullHttpResponse = fullHttpResponse;
        return this;
    }

    public FullHttpResponse getFullHttpResponse(){
        return fullHttpResponse;
    }

    public WebResponse setContent(final byte[] content) {
        this.content = content;
        return this;
    }

    public WebResponse setContent(final String content) {
        if(content != null){
            this.content = content.getBytes(CharsetUtil.UTF_8);
        }
        return this;
    }

    public WebResponse setKeepAlive(final boolean keepAlive) {
        this.keepAlive = keepAlive;
        return this;
    }

    public WebResponse setCtx(ChannelHandlerContext ctx){
        this.ctx = ctx;
        return this;
    }

    public ChannelHandlerContext getCtx(){
       return ctx;
    }

    public void addHeader(final AsciiString name, final String value) {
        fullHttpResponse.headers().add(name, value);
    }

    public void setHeader(final AsciiString name, final String value) {
        fullHttpResponse.headers().set(name, value);
    }

    public void setStatus(final HttpResponseStatus status) {
        fullHttpResponse.setStatus(status);
    }

    public void sendError(final HttpResponseStatus status){
        setStatus(status);
        writeAndFlush();
    }

    public void writeAndFlush(final byte[] content){
        setContent(content);
        writeAndFlush();
    }

    public void writeAndFlush(final String content){
        setContent(content);
        writeAndFlush();
    }

    public void writeAndFlush(){
        final ByteBuf contentBuf = null != content ? Unpooled.copiedBuffer(content) : Unpooled.EMPTY_BUFFER;
        fullHttpResponse = fullHttpResponse.replace(contentBuf);
        if (keepAlive) {
            fullHttpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            fullHttpResponse.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, fullHttpResponse.content().readableBytes());
            ctx.writeAndFlush(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
        }else{
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }
    }

}
