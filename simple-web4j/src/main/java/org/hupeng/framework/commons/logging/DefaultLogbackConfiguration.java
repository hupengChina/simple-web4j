package org.hupeng.framework.commons.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import ch.qos.logback.core.util.OptionHelper;
import org.apache.commons.lang3.StringUtils;
import org.hupeng.framework.commons.EnvironmentConfig;


/**
 * @author : hupeng
 * @date : 2020/8/20
 */
public class DefaultLogbackConfiguration {

    private static final String CONSOLE_LOG_PATTERN = "%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}} "
            + "%clr([${LOG_LEVEL_PATTERN:-%5p}]) ${PID:- } --- [%15.15t] %clr(%-40.40logger{39}){cyan} "
            + ": %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}";

    private static final String FILE_LOG_PATTERN = "%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}} "
            + "${LOG_LEVEL_PATTERN:-%5p} ${PID:- } --- [%t] %-40.40logger{39} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}";

    private static final String MAX_FILE_SIZE = String.valueOf(10*1024*1024);

    private static final long UNBOUNDED_TOTAL_SIZE_CAP = 0;

    private static final Integer MAX_FILE_HISTORY = 7;

    void apply(LogbackConfigurator config) {
        synchronized (config.getConfigurationLock()) {

            config.conversionRule("clr", ColorConverter.class);
            config.conversionRule("wex", WhitespaceThrowableProxyConverter.class);
            config.conversionRule("wEx", ExtendedWhitespaceThrowableProxyConverter.class);

            Appender<ILoggingEvent> consoleAppender = consoleAppender(config);

            String logFilePath = EnvironmentConfig.Logging.getPathProperty();
            if(StringUtils.isBlank(logFilePath)){
                config.root(Level.INFO, consoleAppender);
                return;
            }
            Appender<ILoggingEvent> fileAppender = fileAppender(config,logFilePath);
            config.root(Level.INFO, consoleAppender, fileAppender);
        }
    }

    /**
     * 控制台添加日志
     * @param config
     * @return
     */
    private Appender<ILoggingEvent> consoleAppender(LogbackConfigurator config) {
        ConsoleAppender<ILoggingEvent> appender = new ConsoleAppender<>();
        String logPattern =  EnvironmentConfig.get(EnvironmentConfig.Logging.PATTERN_CONSOLE,CONSOLE_LOG_PATTERN);
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setPattern(OptionHelper.substVars(logPattern, config.getContext()));
        config.start(encoder);
        appender.setEncoder(encoder);
        config.appender("CONSOLE", appender);
        return appender;
    }

    /**
     * 文件方式添加日志
     * @param config
     * @return
     */
    private Appender<ILoggingEvent> fileAppender(LogbackConfigurator config, String logFilePath) {
        RollingFileAppender<ILoggingEvent> appender = new RollingFileAppender<>();
        String logPattern = EnvironmentConfig.get(EnvironmentConfig.Logging.PATTERN_FILE, FILE_LOG_PATTERN);
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setPattern(OptionHelper.substVars(logPattern, config.getContext()));
        appender.setEncoder(encoder);
        //为空默认以FILE_LOG_PATTERN格式加入
        config.start(encoder);
        //文件路径
        appender.setFile(logFilePath);
        setRollingPolicy(appender, config, logFilePath);
        //添加appender
        config.appender("FILE", appender);
        return appender;
    }

    /**
     * SizeAndTimeBasedRollingPolicy设置
     * @param appender
     * @param config
     * @param logFilePath
     */
    private void setRollingPolicy(RollingFileAppender<ILoggingEvent> appender, LogbackConfigurator config,String logFilePath) {
        SizeAndTimeBasedRollingPolicy<ILoggingEvent> rollingPolicy = new SizeAndTimeBasedRollingPolicy<>();

        rollingPolicy.setCleanHistoryOnStart(EnvironmentConfig.get(EnvironmentConfig.Logging.CLEAN_HISTORY_ON_START, Boolean.TYPE,false));
        rollingPolicy.setFileNamePattern(EnvironmentConfig.get(EnvironmentConfig.Logging.ROLLING_FILE_NAME, logFilePath + ".%d{yyyy-MM-dd}.%i.gz"));
        rollingPolicy.setMaxFileSize(EnvironmentConfig.get(EnvironmentConfig.Logging.FILE_MAX_SIZE,MAX_FILE_SIZE));
        rollingPolicy.setMaxHistory(EnvironmentConfig.get(EnvironmentConfig.Logging.FILE_MAX_HISTORY, Integer.TYPE, MAX_FILE_HISTORY));
        rollingPolicy.setTotalSizeCap(new FileSize(EnvironmentConfig.get(EnvironmentConfig.Logging.TOTAL_SIZE_CAP, Long.TYPE, UNBOUNDED_TOTAL_SIZE_CAP)));

        appender.setRollingPolicy(rollingPolicy);
        rollingPolicy.setParent(appender);
        config.start(rollingPolicy);
    }
}
