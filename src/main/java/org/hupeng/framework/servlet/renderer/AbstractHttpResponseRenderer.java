package org.hupeng.framework.servlet.renderer;

import org.hupeng.framework.servlet.HttpProcessContext;

/**
 * @author hupeng
 * @since 2018/7/1.
 */
public abstract class AbstractHttpResponseRenderer implements Renderer {

    @Override
    public void preRender(HttpProcessContext context) { }

    @Override
    public abstract void render(HttpProcessContext context);

    @Override
    public void postRender(HttpProcessContext context) { }
}
