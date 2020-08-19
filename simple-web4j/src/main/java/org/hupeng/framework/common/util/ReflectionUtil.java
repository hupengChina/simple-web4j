package org.hupeng.framework.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author hupeng
 * @since 2018/6/10.
 */
public class ReflectionUtil {
    
    private static final Logger log = LoggerFactory.getLogger(ReflectionUtil.class);

    public static <T> Object newInstance(Class<T> clz) {
        T instance;
        try {
            instance = clz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            log.error("创建实例失败", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 调用方法
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static Object invokeMethod(Object obj, Method method, Object... args) {
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(obj, args);
        } catch (Exception e) {
            log.error("调用方法失败", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置成员变量的值
     * @param obj
     * @param field
     * @param value
     */
    public static void setField(Object obj, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            log.error("设置成员变量失败", e);
            throw new RuntimeException(e);
        }
    }
}
