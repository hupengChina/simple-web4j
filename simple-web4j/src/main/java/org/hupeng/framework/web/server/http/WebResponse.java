package org.hupeng.framework.web.server.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author : hupeng
 * @date : 2020/8/3 16:41
 */
@Data
@Accessors(chain = true)
public class WebResponse {

    FullHttpResponse fullHttpResponse;

    ChannelHandlerContext ctx;

}
