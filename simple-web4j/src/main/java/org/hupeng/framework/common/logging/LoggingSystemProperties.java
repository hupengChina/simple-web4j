package org.hupeng.framework.common.logging;

import org.apache.commons.lang3.StringUtils;
import org.hupeng.framework.common.EnvironmentConfig;

/**
 * @author : hupeng
 * @date : 2020/8/20
 */
public class LoggingSystemProperties {

    public static final String PID_KEY = "PID";

    public static final String EXCEPTION_CONVERSION_WORD = "LOG_EXCEPTION_CONVERSION_WORD";

    public static final String LOG_FILE = "LOG_FILE";

    public static final String LOG_PATH = "LOG_PATH";

    public static final String CONSOLE_LOG_PATTERN = "CONSOLE_LOG_PATTERN";

    public static final String FILE_LOG_PATTERN = "FILE_LOG_PATTERN";

    public static final String FILE_MAX_HISTORY = "LOG_FILE_MAX_HISTORY";

    public static final String FILE_MAX_SIZE = "LOG_FILE_MAX_SIZE";

    public static final String LOG_LEVEL_PATTERN = "LOG_LEVEL_PATTERN";

    public static final String LOG_DATEFORMAT_PATTERN = "LOG_DATEFORMAT_PATTERN";

    public static void apply(){
        setSystemProperty(PID_KEY, null);
        setSystemProperty(EXCEPTION_CONVERSION_WORD, EnvironmentConfig.Logging.getExceptionConversionWord());
        setSystemProperty(CONSOLE_LOG_PATTERN, EnvironmentConfig.Logging.getPatternConsole());
        setSystemProperty(FILE_LOG_PATTERN, EnvironmentConfig.Logging.getPatternFile());
        setSystemProperty(FILE_MAX_HISTORY, EnvironmentConfig.Logging.getFileMaxHistory());
        setSystemProperty(FILE_MAX_SIZE, EnvironmentConfig.Logging.getFileMaxSize());
        setSystemProperty(LOG_LEVEL_PATTERN, EnvironmentConfig.Logging.getPatternLevel());
        setSystemProperty(LOG_DATEFORMAT_PATTERN, EnvironmentConfig.Logging.getPatternDateformat());
    }

    private static void setSystemProperty(String name, String value) {
        if (StringUtils.isNotBlank(value) && System.getProperty(name) == null) {
            System.setProperty(name, value);
        }
    }
}
