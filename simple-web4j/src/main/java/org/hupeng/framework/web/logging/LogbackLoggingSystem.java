package org.hupeng.framework.web.logging;

import ch.qos.logback.classic.LoggerContext;
import org.slf4j.ILoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

/**
 * @author : hupeng
 * @date : 2020/8/19
 */
public class LogbackLoggingSystem {


    private LoggerContext getLoggerContext() {
        ILoggerFactory factory = StaticLoggerBinder.getSingleton().getLoggerFactory();
        return (LoggerContext) factory;
    }
}
