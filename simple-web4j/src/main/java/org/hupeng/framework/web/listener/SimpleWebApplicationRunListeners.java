package org.hupeng.framework.web.listener;

import org.hupeng.framework.context.ConfigurableApplicationContext;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author : hupeng
 * @date : 2020/8/24
 */
public class SimpleWebApplicationRunListeners {

    private final Logger log;

    private final List<SimpleWebApplicationRunListener> listeners;

    public SimpleWebApplicationRunListeners(Logger log, Collection<? extends SimpleWebApplicationRunListener> listeners) {
        this.log = log;
        this.listeners = new ArrayList<>(listeners);
    }

    public void starting() {
        for (SimpleWebApplicationRunListener listener : this.listeners) {
            listener.starting();
        }
    }


    public void contextPrepared(ConfigurableApplicationContext context) {
        for (SimpleWebApplicationRunListener listener : this.listeners) {
            listener.contextPrepared(context);
        }
    }

    public void contextLoaded(ConfigurableApplicationContext context) {
        for (SimpleWebApplicationRunListener listener : this.listeners) {
            listener.contextLoaded(context);
        }
    }

    public void started(ConfigurableApplicationContext context) {
        for (SimpleWebApplicationRunListener listener : this.listeners) {
            listener.started(context);
        }
    }

    public void running(ConfigurableApplicationContext context) {
        for (SimpleWebApplicationRunListener listener : this.listeners) {
            listener.running(context);
        }
    }

    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        for (SimpleWebApplicationRunListener listener : this.listeners) {
            callFailedListener(listener, context, exception);
        }
    }

    private void callFailedListener(SimpleWebApplicationRunListener listener,
                                    ConfigurableApplicationContext context, Throwable exception) {
        try {
            listener.failed(context, exception);
        }
        catch (Throwable ex) {
            this.log.error(ex.getMessage(),exception);
        }
    }
}
