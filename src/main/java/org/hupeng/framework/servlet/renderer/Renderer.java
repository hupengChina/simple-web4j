package org.hupeng.framework.servlet.renderer;

import org.hupeng.framework.servlet.HttpProcessContext;

/**
 * @author hupeng
 * @since 2018/7/1.
 */
public interface Renderer {

    void preRender(final HttpProcessContext context);

    void render(final HttpProcessContext context);

    void postRender(final HttpProcessContext context);
}
