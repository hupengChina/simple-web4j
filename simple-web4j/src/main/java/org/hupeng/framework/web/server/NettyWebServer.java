package org.hupeng.framework.web.server;

import org.hupeng.framework.common.EnvironmentConfig;
import org.hupeng.framework.common.util.StringUtil;

public class NettyWebServer implements WebServer {

    private static int port = 80;

    @Override
    public void start() {
        String portString = EnvironmentConfig.Server.getPort();
        if(portString != null && StringUtil.isInt(portString)){
            port = Integer.valueOf(portString);
        }
        new Server(port).start();
    }

    @Override
    public void stop() {

    }

    @Override
    public int getPort() {
        return 0;
    }
}
