package org.hupeng.framework.servlet.handler;

import org.hupeng.framework.servlet.HttpProcessContext;
import org.hupeng.framework.servlet.HttpProcessControl;

import java.io.IOException;

/**
 *
 * @author hupeng
 * @since 2018/6/24.
 */
public interface Handler {

    /**
     * 实现
     * @param processContext
     * @param processControl
     */
    void handle(final HttpProcessContext processContext, final HttpProcessControl processControl);
}
