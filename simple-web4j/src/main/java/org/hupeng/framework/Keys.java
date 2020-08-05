package org.hupeng.framework;

/**
 * @author hupeng
 * @since 2018/7/1.
 */
public class Keys {

    public static final String CONFIG_FILE = "config.properties";

    public static final class Server {

        public static final String PORT = "server.port";

        public static final String STATIC_RESOURCE_PATH = "server.resources.static-locations";

        private Server() { }
    }

    private Keys() { }
}
