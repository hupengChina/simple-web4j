package org.hupeng.framework.web.server.http;

import io.netty.handler.codec.http.FullHttpRequest;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.jws.HandlerChain;

/**
 * @author : hupeng
 * @date : 2020/8/3 16:41
 */
@Data
@Accessors(chain = true)
public class WebRequest {

    FullHttpRequest fullHttpRequest;
}
