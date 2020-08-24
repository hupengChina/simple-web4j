package org.hupeng.framework.web.listener;

import org.hupeng.framework.context.ApplicationListener;
import org.hupeng.framework.context.ConfigurableApplicationContext;
import org.hupeng.framework.context.GenericApplicationListener;
import org.hupeng.framework.context.event.ApplicationEvent;
import org.hupeng.framework.context.support.ApplicationContextAware;
import org.hupeng.framework.web.SimpleWebApplication;
import org.hupeng.framework.web.event.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author : hupeng
 * @date : 2020/8/24
 */
public class EventPublishingRunListener implements SimpleWebApplicationRunListener {

    private final SimpleWebApplication application;

    private final String[] args;

    private final Collection<ApplicationListener<?>> listeners = new ArrayList<>();

    public EventPublishingRunListener(SimpleWebApplication application, String[] args) {
        super();
        this.application = application;
        this.args = args;
        for (ApplicationListener<?> listener : application.getListeners()) {
            listeners.add(listener);
        }
    }

    @Override
    public void starting() {
        for (ApplicationListener listener: listeners) {
            if(listener instanceof GenericApplicationListener){
                if(!((GenericApplicationListener) listener).supportsEventType(ApplicationStartingEvent.class)){
                    continue;
                }
            }
            listener.onApplicationEvent(new ApplicationStartingEvent(this.application, this.args));
        }
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        for (ApplicationListener listener: listeners) {
            if(listener instanceof GenericApplicationListener){
                if(!((GenericApplicationListener) listener).supportsEventType(ApplicationPreparedEvent.class)){
                    continue;
                }
            }
            listener.onApplicationEvent(new ApplicationPreparedEvent(this.application, this.args,context));
        }
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        for (ApplicationListener<?> listener : this.application.getListeners()) {
            if (listener instanceof ApplicationContextAware) {
                ((ApplicationContextAware) listener).setApplicationContext(context);
            }
            context.addApplicationListener(listener);
        }
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        context.publishEvent(new ApplicationStartedEvent(this.application, this.args, context));
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        context.publishEvent(new ApplicationReadyEvent(this.application, this.args, context));
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        context.publishEvent(new ApplicationFailedEvent(this.application, this.args, context, exception));
    }
}
