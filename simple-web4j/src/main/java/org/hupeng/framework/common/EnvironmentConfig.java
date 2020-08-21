package org.hupeng.framework.common;

import org.hupeng.framework.common.util.PropsUtil;

import java.util.Properties;

/**
 * @author hupeng
 * @since 2018/7/1
 */
public class EnvironmentConfig {

    private static final String CONFIG_FILE = "config.properties";

    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(EnvironmentConfig.CONFIG_FILE);

    private static String get(String key){
        return PropsUtil.get(CONFIG_PROPS,key);
    }

    public static String get(String key, String defaultValue){
        return PropsUtil.get(CONFIG_PROPS,key,defaultValue);
    }

    public static <T> T get(String key, Class<T> returnType) {
        return PropsUtil.get(CONFIG_PROPS, key, returnType);
    }

    public static <T> T get(String key, Class<T> returnType, T defaultValue){
        return PropsUtil.get(CONFIG_PROPS, key, returnType,defaultValue);
    }

    public static final class Server {

        public static final String PORT = "server.port";

        public static final String STATIC_RESOURCE_PATH = "server.resources.static-locations";

        public static String getPort() {
            return get(PORT);
        }

        public static String getStaticResourcePath() {
            return get(STATIC_RESOURCE_PATH);
        }

        private Server() {}
    }

    public static final class Logging {

        public static final String PATH_PROPERTY = "logging.path";

        public static final String PATTERN_FILE = "logging.pattern.file";

        public static final String FILE_MAX_HISTORY = "logging.file.max-history";

        public static final String FILE_MAX_SIZE = "logging.file.max-size";

        public static final String TOTAL_SIZE_CAP = "logging.file.total-size-cap";

        public static final String CLEAN_HISTORY_ON_START = "logging.file.clean-history-on-start";

        public static final String EXCEPTION_CONVERSION_WORD = "logging.exception-conversion-word";

        public static final String PATTERN_CONSOLE = "logging.pattern.console";

        public static final String PATTERN_LEVEL = "logging.pattern.level";

        public static final String PATTERN_DATEFORMAT = "logging.pattern.dateformat";

        public static final String ROLLING_FILE_NAME = "logging.pattern.rolling-file-name";

        public static String getPathProperty() {
            return get(PATH_PROPERTY);
        }

        public static String getCleanHistoryOnStart() {
            return get(CLEAN_HISTORY_ON_START);
        }

        public static String getRollingFileName() {
            return get(ROLLING_FILE_NAME);
        }

        public static String getTotalSizeCap() {
            return get(TOTAL_SIZE_CAP);
        }

        public static String getExceptionConversionWord() {
            return get(EXCEPTION_CONVERSION_WORD);
        }

        public static String getPatternConsole() {
            return get(PATTERN_CONSOLE);
        }

        public static String getPatternFile() {
            return get(PATTERN_FILE);
        }

        public static String getFileMaxHistory() {
            return get(FILE_MAX_HISTORY);
        }

        public static String getFileMaxSize() {
            return get(FILE_MAX_SIZE);
        }

        public static String getPatternLevel() {
            return get(PATTERN_LEVEL);
        }

        public static String getPatternDateformat() {
            return get(PATTERN_DATEFORMAT);
        }

        private Logging() { }
    }

    private EnvironmentConfig() { }
}
