package org.hupeng.framework.web.handler;

import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

/**
 * 静态资源处理Handler
 * @author hupeng
 * @since 2018/6/24.
 */
public class StaticResourceHandlerAdapter implements HandlerAdapter{


    @Override
    public void handle(WebRequest request, WebResponse response, Handler handler, HandlerAdapterController handlerAdapterController) {
        //静态资源返回
//        if(StaticResources.isStatic(request)){
//            //请求作用域放入对应资源渲染器
//            return;
//        }
        //执行下一个处理器
        handlerAdapterController.nextHandler();
    }
}
