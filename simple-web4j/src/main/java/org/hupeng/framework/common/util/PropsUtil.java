package org.hupeng.framework.common.util;

import com.sun.istack.internal.Nullable;
import org.omg.CORBA.OBJ_ADAPTER;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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

    @Nullable
    public static String get(Properties props, String key) {
        return props.getProperty(key);
    }

    @Nullable
    public static String get(Properties props, String key, String defaultValue) {
        return props.getProperty(key,defaultValue);
    }

    @Nullable
    public static <T> T get(Properties props, String key, Class<T> returnType) {
        return get(props,key,returnType, null);
    }

    @Nullable
    public static <T> T get(Properties props, String key, Class<T> returnType, T defaultValue){
        T value =  (T) getObject(props, key, returnType);
        if(value == null){
            return defaultValue;
        }
        return value;
    }

    @Nullable
    public static Object getObject(Properties props, String key, Class returnType) {
        String value = props.getProperty(key);
        if(value == null){
            return null;
        }
        if(returnType.isAssignableFrom(Boolean.TYPE)){
            return Boolean.valueOf(value);
        }
        if(returnType.isAssignableFrom(Integer.TYPE)){
            return Boolean.valueOf(value);
        }
        if(returnType.isAssignableFrom(Long.TYPE)){
            return Boolean.valueOf(value);
        }
        if(returnType.isAssignableFrom(Float.TYPE)){
            return Float.valueOf(value);
        }
        if(returnType.isAssignableFrom(Double.TYPE)){
            return Double.valueOf(value);
        }
        if(returnType.isAssignableFrom(BigDecimal.class)){
            return new BigDecimal(value);
        }
        if(returnType.isAssignableFrom(String.class)){
            return value;
        }
        throw new IllegalArgumentException("不支持解析类型："+returnType.getName());
    }
}
