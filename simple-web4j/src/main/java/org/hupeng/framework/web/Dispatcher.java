package org.hupeng.framework.web;

import org.hupeng.framework.web.handler.*;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : hupeng
 * @date : 2020/8/3 11:31
 */
public class Dispatcher {

    private static final List<HandlerAdapter> handlerAdapters = new ArrayList<HandlerAdapter>();

    static {
        handlerAdapters.add(new StaticResourceHandlerAdapter());
        handlerAdapters.add(new RequestDispatchHandlerAdapter());
    }


    public static void doService(WebRequest webRequest, WebResponse webResponse, Handler handler){
        HandlerAdapterController handlerAdapterController = new HandlerAdapterController(handlerAdapters.iterator(),webRequest,webResponse,handler);
        handlerAdapterController.nextHandler();
    }
}
