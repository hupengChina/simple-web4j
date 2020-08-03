package com.demo;

import org.hupeng.framework.ioc.SingletonInstanceContext;
import org.hupeng.framework.web.handler.HandlerMapping;
import org.hupeng.framework.web.server.Server;

/**
 * @author : hupeng
 * @date : 2020/8/3 16:08
 */
public class TestServer {

    public static void main(String[] args) {
        SingletonInstanceContext.getInstance().init(TestServer.class.getPackage().getName());
        HandlerMapping.init(SingletonInstanceContext.getInstance().getClasses());
        new Server(8080).start();
    }
}
