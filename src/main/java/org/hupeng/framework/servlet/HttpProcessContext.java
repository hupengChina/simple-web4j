package org.hupeng.framework.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http请求封装context
 * @author hupeng
 * @since 2018/6/24.
 */
public class HttpProcessContext {

    private HttpServletRequest request;

    private HttpServletResponse response;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpProcessContext(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

}
