package org.hupeng.framework.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author hupeng
 * @since 2018/6/10.
 */
@Slf4j
public class ReflectionUtil {

    public static <T> Object newInstance(Class<T> clz) {
        T instance;
        try {
            instance = clz.newInstance();
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
