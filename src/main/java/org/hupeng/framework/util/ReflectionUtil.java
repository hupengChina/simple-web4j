package org.hupeng.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hupeng
 * @since 2018/6/10.
 */
public class ReflectionUtil {

    private static Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    public static <T> Object newInstance(Class<T> clz) {
        T instance;
        try {
            instance = clz.newInstance();
        } catch (Exception e) {
            logger.error("创建实例失败", e);
            throw new RuntimeException(e);
        }
        return instance;
    }
}
