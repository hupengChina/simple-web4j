package org.hupeng.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author hupeng
 * @since 2018/7/1.
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
}
