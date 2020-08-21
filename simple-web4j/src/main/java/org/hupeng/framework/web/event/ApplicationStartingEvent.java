package org.hupeng.framework.web.event;

import org.hupeng.framework.web.SimpleWebApplication;

/**
 * @author : hupeng
 * @date : 2020/8/21
 */
public class ApplicationStartingEvent extends SimpleWebApplicationEvent {

    public ApplicationStartingEvent(SimpleWebApplication application, String[] args) {
        super(application, args);
    }
}
