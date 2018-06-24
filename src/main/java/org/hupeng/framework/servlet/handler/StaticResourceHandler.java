package org.hupeng.framework.servlet.handler;

import org.hupeng.framework.servlet.HttpProcessContext;
import org.hupeng.framework.servlet.HttpProcessControl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 * 静态资源处理Handler
 * @author hupeng
 * @since 2018/6/24.
 */
public class StaticResourceHandler implements Handler{

    private static final String SERVLET_NAME = "SimpleFileServlet";

    private RequestDispatcher requestDispatcher;


    public StaticResourceHandler(final ServletContext servletContext) {

        requestDispatcher = servletContext.getNamedDispatcher(SERVLET_NAME);

        if (null != requestDispatcher) {

        }else{

        }
    }

    public void handle(HttpProcessContext processContext, HttpProcessControl processControl) {


        //执行下一个处理器
        processControl.nextHandler();
    }
}
