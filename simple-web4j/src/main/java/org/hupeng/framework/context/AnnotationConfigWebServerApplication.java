package org.hupeng.framework.context;

import org.hupeng.framework.web.server.NettyWebServer;
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
            this.webServer = new NettyWebServer();
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
