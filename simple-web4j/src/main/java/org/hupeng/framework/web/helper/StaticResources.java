package org.hupeng.framework.web.helper;

import org.hupeng.framework.web.Keys;
import org.hupeng.framework.util.AntPathMatcher;
import org.hupeng.framework.util.PropsUtil;

import java.util.Properties;

/**
 * @author hupeng
 * @since 2018/7/1
 */
public class StaticResources {

    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(Keys.CONFIG_FILE);

    /**
     * 静态资源路径
     * @return path
     */
    public static String getPath() {
        return PropsUtil.getString(CONFIG_PROPS, Keys.Server.STATIC_RESOURCE_PATH);
    }

    public static String getConfigValue(String key) {
        return PropsUtil.getString(CONFIG_PROPS,key);
    }

    /**
     * 静态资源判断
     * @param uri String
     * @return isStatic
     */
    public static boolean isStatic(String uri) {
        return AntPathMatcher.match(getPath(), uri);
    }

}
