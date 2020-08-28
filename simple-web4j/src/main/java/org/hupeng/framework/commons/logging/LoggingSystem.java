package org.hupeng.framework.commons.logging;

import java.util.EnumSet;
import java.util.Set;

/**
 * @author : hupeng
 * @date : 2020/8/19
 */
public interface LoggingSystem {

    void beforeInitialize();

    void initialize();

    void cleanUp();

    Runnable getShutdownHandler();

    default Set<LogLevel> getSupportedLogLevels() {
        return EnumSet.allOf(LogLevel.class);
    }

    void setLogLevel(String loggerName, LogLevel level);
}
