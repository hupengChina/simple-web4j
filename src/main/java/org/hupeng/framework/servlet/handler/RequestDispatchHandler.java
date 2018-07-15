package org.hupeng.framework.servlet.handler;

import org.hupeng.framework.ioc.BeanManager;
import org.hupeng.framework.ioc.Lifecycle;
import org.hupeng.framework.servlet.HttpProcessContext;
import org.hupeng.framework.servlet.HttpProcessControl;

/**
 * @author hupeng
 * @since 2018/7/8.
 */
public class RequestDispatchHandler implements Handler {



    @Override
    public void handle(HttpProcessContext processContext, HttpProcessControl processControl) {
        BeanManager beanManager = Lifecycle.getBeanManager();
        beanManager.getBeans("");
    }
}
