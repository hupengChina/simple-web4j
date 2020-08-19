package org.hupeng.framework.common.logging;

import ch.qos.logback.classic.LoggerContext;
import org.slf4j.ILoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

import java.util.Set;

/**
 * @author : hupeng
 * @date : 2020/8/19
 */
public class LogbackLoggingSystem implements LoggingSystem {

    private LoggerContext getLoggerContext() {
        ILoggerFactory factory = StaticLoggerBinder.getSingleton().getLoggerFactory();
        return (LoggerContext) factory;
    }

    @Override
    public void beforeInitialize() {

    }

    @Override
    public void initialize() {

    }

    @Override
    public void cleanUp() {

    }

    @Override
    public Runnable getShutdownHandler() {
        return null;
    }

    @Override
    public Set<LogLevel> getSupportedLogLevels() {
        return null;
    }

    @Override
    public void setLogLevel(String loggerName, LogLevel level) {

    }
}
