package org.hupeng.framework.web.handler;

import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.hupeng.framework.ioc.SingletonInstanceContext;
import org.hupeng.framework.util.JsonUtil;
import org.hupeng.framework.util.ReflectionUtil;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

import java.lang.reflect.Method;

/**
 * @author hupeng
 * @since 2018/7/8.
 */
public class RequestDispatchHandlerAdapter implements HandlerAdapter {

    @Override
    public void handle(WebRequest webRequest, WebResponse webResponse, Handler handler, HandlerAdapterController handlerAdapterController) {

        HttpMethod method = webRequest.getFullHttpRequest().method();
        String content = webRequest.getFullHttpRequest().content().toString(CharsetUtil.UTF_8);

        if(handler != null){
            Class controllerClass = handler.getControllerClass();
            Method actionMethod = handler.getMethod();
            Object controller = null;
            try {
                controller = SingletonInstanceContext.getInstance().get(controllerClass);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            Object result = ReflectionUtil.invokeMethod(controller, actionMethod);

            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.copiedBuffer(JsonUtil.toJson(result), CharsetUtil.UTF_8));
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
            webResponse.setFullHttpResponse(response);
        }
    }
}
