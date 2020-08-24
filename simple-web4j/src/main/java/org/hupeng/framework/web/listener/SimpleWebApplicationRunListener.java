package org.hupeng.framework.web.listener;

import org.hupeng.framework.context.ConfigurableApplicationContext;

/**
 * @author : hupeng
 * @date : 2020/8/21
 */
public interface SimpleWebApplicationRunListener {

    void starting();

    void contextPrepared(ConfigurableApplicationContext context);

    void contextLoaded(ConfigurableApplicationContext context);

    void started(ConfigurableApplicationContext context);

    void running(ConfigurableApplicationContext context);

    void failed(ConfigurableApplicationContext context, Throwable exception);
}
