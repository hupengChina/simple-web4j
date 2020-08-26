package org.hupeng.framework.web;

import org.hupeng.framework.context.AnnotationConfigApplicationContext;
import org.hupeng.framework.web.server.netty.WebNettyServer;
import org.hupeng.framework.web.server.WebServer;

public class AnnotationConfigWebServerApplication extends AnnotationConfigApplicationContext {

    private volatile WebServer webServer;

    @Override
    protected void onRefresh() {
        createWebServer();
    }

    @Override
    protected void finishRefresh() {
        super.finishRefresh();
        startWebServer();
    }


    private void createWebServer() {
        WebServer webServer = this.webServer;
        if (webServer == null) {
            //todo 暂时只做netty实现，不排除其他实现
            this.webServer = new WebNettyServer(this);
        }
    }

    private WebServer startWebServer(){
        WebServer webServer = this.webServer;
        if (webServer != null) {
            webServer.start();
        }
        return webServer;
    }
}
