package org.hupeng.framework.servlet;

import org.hupeng.framework.servlet.handler.Handler;
import org.hupeng.framework.servlet.handler.RequestDispatchHandler;
import org.hupeng.framework.servlet.handler.StaticResourceHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 请求控制器
 * @author hupeng
 * @since 2018/6/10.
 */
public class DispatcherServlet extends HttpServlet {

    /**
     * 处理器集合
     */
    private static final List<Handler> HANDLER = new ArrayList<Handler>();

    /**
     * handle初始化
     */
    @Override
    public void init() throws ServletException {
        HANDLER.add(new StaticResourceHandler(getServletContext()));
        HANDLER.add(new RequestDispatchHandler());
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) {
        HttpProcessContext processContext = new HttpProcessContext(req,resp);
        HttpProcessControl processControl = new HttpProcessControl(HANDLER.iterator(),processContext);
        //开始执行
        processControl.nextHandler();

    }

}
