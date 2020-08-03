package org.hupeng.framework.web.handler;

import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author : hupeng
 * @date : 2020/8/3 16:26
 */
public class HandlerAdapterController {

    private Iterator<HandlerAdapter> handlerIterables;
    private WebRequest request;
    private WebResponse response;
    private Handler handler;

    public HandlerAdapterController(final Iterator<HandlerAdapter> handlerIterables, final WebRequest request, final WebResponse response, final Handler handler) {
        this.handlerIterables = handlerIterables;
        this.request = request;
        this.response = response;
        this.handler = handler;
    }

    /**
     * 根据初始化的handler集合，遍历依次执行对应的handle（）
     */
    public void nextHandler() {
        if(handlerIterables.hasNext()){
            HandlerAdapter handlerAdapter = handlerIterables.next();
            handlerAdapter.handle(request,response,handler,this);
        }
    }
}
