package com.demo;

import org.hupeng.framework.servlet.AbstractServletListener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpSessionEvent;

public class HelloServletListener extends AbstractServletListener {

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }
}
