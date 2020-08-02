package org.hupeng.framework.servlet.handler;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hupeng.framework.ioc.SingletonInstanceContext;
import org.hupeng.framework.servlet.HttpProcessContext;
import org.hupeng.framework.servlet.HttpProcessControl;
import org.hupeng.framework.servlet.action.ActionMapping;
import org.hupeng.framework.servlet.action.ActionInfo;
import org.hupeng.framework.util.JsonUtil;
import org.hupeng.framework.util.ReflectionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author hupeng
 * @since 2018/7/8.
 */
public class RequestDispatchHandler implements Handler {



    @Override
    public void handle(HttpProcessContext processContext, HttpProcessControl processControl) {
        HttpServletRequest req = processContext.getRequest();
        String requestPath = req.getPathInfo();
        ActionInfo actionInfo = ActionMapping.getAction(requestPath);
        if(actionInfo != null) {
            Class controllerClass = actionInfo.getControllerClass();
            Method actionMethod = actionInfo.getMethod();
            Object controller = null;
            try {
                controller = SingletonInstanceContext.getInstance().get(controllerClass);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            Object result = ReflectionUtil.invokeMethod(controller, actionMethod);
            if (result != null) {
                HttpServletResponse resp = processContext.getResponse();
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                PrintWriter writer = null;
                try {
                    writer = resp.getWriter();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                writer.write(JsonUtil.toJson(result));
                writer.flush();
                writer.close();
            }
        }
    }
}
