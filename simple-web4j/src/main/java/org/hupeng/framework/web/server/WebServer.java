package org.hupeng.framework.web.server;

public interface WebServer {

    void start();

    void stop();

    int getPort();
}
