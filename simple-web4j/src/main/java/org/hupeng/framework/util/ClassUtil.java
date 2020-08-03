package org.hupeng.framework.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hupeng
 * @since 2018/6/18.
 */
public class ClassUtil {

    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static Class<?> loadClass(String className, boolean isInitialized) throws ClassNotFoundException {
        return Class.forName(className, isInitialized, getClassLoader());
    }

}
