package org.hupeng.framework.servlet;

import lombok.Data;
import org.hupeng.framework.servlet.renderer.Renderer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http请求封装context
 * @author hupeng
 * @since 2018/6/24.
 */
@Data
public class HttpProcessContext {

    private HttpServletRequest request;

    private HttpServletResponse response;

    private Renderer renderer;

    public HttpProcessContext(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
}
