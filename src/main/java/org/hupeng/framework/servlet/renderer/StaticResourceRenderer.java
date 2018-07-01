package org.hupeng.framework.servlet.renderer;

import org.hupeng.framework.servlet.HttpProcessContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author hupeng
 * @since 2018/7/1.
 */
public class StaticResourceRenderer extends AbstractHttpResponseRenderer {


    private RequestDispatcher requestDispatcher;

    public StaticResourceRenderer(final RequestDispatcher requestDispatcher) {
        this.requestDispatcher = requestDispatcher;
    }

    public void render(HttpProcessContext context) {
        try {
            requestDispatcher.forward(context.getRequest(),context.getResponse());
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
