package org.hupeng.framework.servlet.handler;

import org.hupeng.framework.helper.StaticResources;
import org.hupeng.framework.servlet.HttpProcessContext;
import org.hupeng.framework.servlet.HttpProcessControl;
import org.hupeng.framework.servlet.renderer.StaticResourceRenderer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 * 静态资源处理Handler
 * @author hupeng
 * @since 2018/6/24.
 */
public class StaticResourceHandler implements Handler{

    /** Default Servlet name used by Tomcat, Jetty, JBoss, and GlassFish */
    private static final String COMMON_DEFAULT_SERVLET_NAME = "default";

    /** Default Servlet name used by Google App Engine */
    private static final String GAE_DEFAULT_SERVLET_NAME = "_ah_default";

    /** Default Servlet name used by Resin */
    private static final String RESIN_DEFAULT_SERVLET_NAME = "resin-file";

    /** Default Servlet name used by WebLogic */
    private static final String WEBLOGIC_DEFAULT_SERVLET_NAME = "FileServlet";

    /** Default Servlet name used by WebSphere */
    private static final String WEBSPHERE_DEFAULT_SERVLET_NAME = "SimpleFileServlet";

    private RequestDispatcher requestDispatcher;


    public StaticResourceHandler(final ServletContext servletContext) {
        this.requestDispatcher = servletContext.getNamedDispatcher(COMMON_DEFAULT_SERVLET_NAME);
        if (null != this.requestDispatcher) {

        }
    }

    public void handle(HttpProcessContext processContext, HttpProcessControl processControl) {
        //静态资源返回
        if(StaticResources.isStatic(processContext.getRequest())){
            //请求作用域放入对应资源渲染器
            processContext.setRenderer(new StaticResourceRenderer(requestDispatcher));
            return;
        }
        //执行下一个处理器
        processControl.nextHandler();
    }
}
