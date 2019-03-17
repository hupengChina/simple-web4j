/*
 * Copyright (c) 2009-2019, b3log.org & hacpai.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hupeng.framework.servlet;


import org.hupeng.framework.ioc.DefaultServletContext;
import org.hupeng.framework.ioc.SingletonInstanceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public abstract class AbstractServletListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractServletListener.class);

    private static ServletContext servletContext;

    static {
        servletContext = new DefaultServletContext();
    }

    public static ServletContext getServletContext() {
        if (null == servletContext) {
            throw new IllegalStateException("初始化servlet context失败!");
        }
        return servletContext;
    }

    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent) {
        servletContext = servletContextEvent.getServletContext();
        try {
            //根据包路径，初始化对应单例容器
            SingletonInstanceContext.getInstance().init("");
        } catch (final Exception e) {
            throw new IllegalStateException("初始化bean失败");
        }
    }

    @Override
    public void contextDestroyed(final ServletContextEvent servletContextEvent) {
        LOGGER.info("关闭容器");
    }

    @Override
    public abstract void requestDestroyed(final ServletRequestEvent servletRequestEvent);

    @Override
    public abstract void requestInitialized(final ServletRequestEvent servletRequestEvent);

    @Override
    public abstract void sessionCreated(final HttpSessionEvent httpSessionEvent);

    @Override
    public void sessionDestroyed(final HttpSessionEvent httpSessionEvent) {
    }
}
