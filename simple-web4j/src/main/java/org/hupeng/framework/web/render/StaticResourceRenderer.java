package org.hupeng.framework.web.render;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.cookie.ServerCookieEncoder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.Tika;
import org.hupeng.framework.web.handler.HandleResult;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

import java.io.File;
import java.io.IOException;

/**
 * @author : hupeng
 * @date : 2020/8/4 17:32
 */
public class StaticResourceRenderer implements Renderer {

    @Override
    public void render(WebRequest request, WebResponse response, HandleResult handleResult) {
        String uri = request.getFullHttpRequest().uri();
        String path = StaticResourceRenderer.class.getResource("/").getPath();
        if (StringUtils.contains(path, "/target/classes/") || StringUtils.contains(path, "/target/test-classes/")) {
            path = StringUtils.replace(path, "/target/classes/", "/src/main/resources");
            path = StringUtils.replace(path, "/target/test-classes/", "/src/main/resources");
        }
        path += uri;
        byte[] bytes = new byte[0];
        try {
            path = path.substring(1,path.length());
            bytes = FileUtils.readFileToByteArray(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        final String contentType = new Tika().detect(uri);
        ChannelHandlerContext ctx = response.getCtx();
        FullHttpResponse resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        resp.headers().set(HttpHeaderNames.CONTENT_TYPE, contentType);
        final ByteBuf contentBuf = (null != bytes) ? Unpooled.copiedBuffer(bytes) : Unpooled.EMPTY_BUFFER;
        resp = resp.replace(contentBuf);
        if (null != ctx) {
            ctx.write(resp);
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        }
    }

}
