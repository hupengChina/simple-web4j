package org.hupeng.framework.web.handler;

public interface HandlerMapping {

    Object getHandler(String requestPath);

}
