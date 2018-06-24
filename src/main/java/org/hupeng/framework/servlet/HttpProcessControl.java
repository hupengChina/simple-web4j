package org.hupeng.framework.servlet;

import org.hupeng.framework.servlet.handler.Handler;

import java.util.Iterator;

/**
 * http请求流程Control
 * @author hupeng
 * @since 2018/6/24.
 */
public class HttpProcessControl {

    private Iterator<Handler> handlerIterables;

    private HttpProcessContext httpProcessContext;

    public HttpProcessControl(final Iterator<Handler> handlerIterables, final HttpProcessContext httpProcessContext) {
        this.handlerIterables = handlerIterables;
        this.httpProcessContext = httpProcessContext;
    }

    /**
     * 根据初始化的handler集合，遍历依次执行对应的handle（）
     */
    public void nextHandler() {
          if(handlerIterables.hasNext()){
                Handler handler = handlerIterables.next();
                handler.handle(httpProcessContext,this);
          }
    }

}
