package com.demo;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;

public class Starter {
    public static void main(String[] args) {
        String classesPath = ClassLoader.getSystemResource("").getPath(); // Real path including maven sub folder
        String webappDirLocation = classesPath.replace("target/classes/", "src/main/webapp/"); // POM structure in dev env
        final File file = new File(webappDirLocation);
        if (!file.exists()) {
            webappDirLocation = "."; // production environment
        }
        final Server server = new Server(8080);
        final WebAppContext root = new WebAppContext();
        root.setParentLoaderPriority(true); // Use parent class loader
        root.setContextPath("/");
        root.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
        root.setResourceBase(webappDirLocation);
        server.setHandler(root);
        try {
            server.start();
        } catch (final Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
