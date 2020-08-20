package org.hupeng.framework.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author hupeng
 * @since 2018/7/1
 */
public class PropsUtil {

    public static Properties loadProps(String fileName) {
        Properties props = null;
        InputStream is = null;
        try {
            props = new Properties();
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return props;
    }

    public static String getString(Properties props, String key) {
        return props.getProperty(key);
    }

    public static String getString(Properties props, String key, String defaultValue) {
        return props.getProperty(key,defaultValue);
    }

    public static Boolean getBoolean(Properties props, String key, Boolean defaultValue) {
        String value = props.getProperty(key);
        if (value != null) {
            if("true".equals(value)){
                return true;
            }
            if("false".equals(value)){
                return false;
            }
        }
        return null;
    }

    public static Integer getInteger(Properties props, String key, Integer defaultValue) {
        String value = props.getProperty(key);
        if (value != null) {
            return Integer.valueOf(value);
        }
        return defaultValue;
    }

    public static Long getLong(Properties props, String key, Long defaultValue) {
        String value = props.getProperty(key);
        if (value != null) {
            return Long.valueOf(value);
        }
        return defaultValue;
    }
}
