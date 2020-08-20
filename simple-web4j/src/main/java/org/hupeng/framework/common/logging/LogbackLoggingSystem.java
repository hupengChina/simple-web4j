package org.hupeng.framework.common.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import org.apache.commons.lang3.StringUtils;
import org.hupeng.framework.common.EnvironmentConfig;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.impl.StaticLoggerBinder;

import java.util.*;

/**
 * @author : hupeng
 * @date : 2020/8/19
 */
public class LogbackLoggingSystem implements LoggingSystem {

    public static final String ROOT_LOGGER_NAME = "ROOT";

    private static final LogLevels<Level> LEVELS = new LogLevels<>();

    static {
        LEVELS.map(LogLevel.TRACE, Level.TRACE);
        LEVELS.map(LogLevel.TRACE, Level.ALL);
        LEVELS.map(LogLevel.DEBUG, Level.DEBUG);
        LEVELS.map(LogLevel.INFO, Level.INFO);
        LEVELS.map(LogLevel.WARN, Level.WARN);
        LEVELS.map(LogLevel.ERROR, Level.ERROR);
        LEVELS.map(LogLevel.FATAL, Level.ERROR);
        LEVELS.map(LogLevel.OFF, Level.OFF);
    }

    private static final TurboFilter FILTER = new TurboFilter() {

        @Override
        public FilterReply decide(Marker marker, ch.qos.logback.classic.Logger logger,
                                  Level level, String format, Object[] params, Throwable t) {
            return FilterReply.DENY;
        }

    };

    private LoggerContext getLoggerContext() {
        ILoggerFactory factory = StaticLoggerBinder.getSingleton().getLoggerFactory();
        return (LoggerContext) factory;
    }

    @Override
    public void beforeInitialize() {
        LoggerContext loggerContext = getLoggerContext();
        loggerContext.getTurboFilterList().add(FILTER);
    }

    @Override
    public void initialize() {
        LoggerContext loggerContext = getLoggerContext();
        applySystemProperties();
        loadDefaults(loggerContext);
        loggerContext.getTurboFilterList().remove(FILTER);
    }

    @Override
    public void cleanUp() {
        LoggerContext context = getLoggerContext();
        context.getStatusManager().clear();
    }

    @Override
    public Runnable getShutdownHandler() {
        return new ShutdownHandler();
    }


    @Override
    public void setLogLevel(String loggerName, LogLevel level) {
        LoggerContext loggerContext = getLoggerContext();
        if (StringUtils.isEmpty(loggerName) || ROOT_LOGGER_NAME.equals(loggerName)) {
            loggerName = Logger.ROOT_LOGGER_NAME;
        }
        ch.qos.logback.classic.Logger logger = loggerContext.getLogger(loggerName);
        if (logger != null) {
            logger.setLevel(LEVELS.convertSystemToNative(level));
        }
    }

    protected final void applySystemProperties(){
        LoggingSystemProperties.apply();
    }


    protected void stopAndReset(LoggerContext loggerContext) {
        loggerContext.stop();
        loggerContext.reset();
    }

    protected void loadDefaults(LoggerContext loggerContext) {

        stopAndReset(loggerContext);
        LogbackConfigurator configurator = new LogbackConfigurator(loggerContext);
        loggerContext.putProperty(LoggingSystemProperties.LOG_LEVEL_PATTERN,
                EnvironmentConfig.get(EnvironmentConfig.Logging.PATTERN_LEVEL,"%5p"));
        loggerContext.putProperty(LoggingSystemProperties.LOG_DATEFORMAT_PATTERN,
                EnvironmentConfig.get(EnvironmentConfig.Logging.PATTERN_DATEFORMAT,"yyyy-MM-dd HH:mm:ss.SSS"));
        new DefaultLogbackConfiguration().apply(configurator);
        loggerContext.setPackagingDataEnabled(true);
    }

    private static class LogLevels<T> {

        private final Map<LogLevel, T> systemToNative;

        private final Map<T, LogLevel> nativeToSystem;

        public LogLevels() {
            this.systemToNative = new EnumMap<>(LogLevel.class);
            this.nativeToSystem = new HashMap<>();
        }

        public void map(LogLevel system, T nativeLevel) {
            this.systemToNative.putIfAbsent(system, nativeLevel);
            this.nativeToSystem.putIfAbsent(nativeLevel, system);
        }

        public LogLevel convertNativeToSystem(T level) {
            return this.nativeToSystem.get(level);
        }

        public T convertSystemToNative(LogLevel level) {
            return this.systemToNative.get(level);
        }

        public Set<LogLevel> getSupported() {
            return new LinkedHashSet<>(this.nativeToSystem.values());
        }

    }

    private final class ShutdownHandler implements Runnable {

        @Override
        public void run() {
            getLoggerContext().stop();
        }

    }
}
