package org.hupeng.framework.web.handler;

import org.hupeng.framework.web.render.Renderer;

/**
 * @author : hupeng
 * @date : 2020/8/4
 */
public class HandleResult {

    Object result;

    Renderer renderer;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }
}
