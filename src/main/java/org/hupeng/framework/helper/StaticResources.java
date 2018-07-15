package org.hupeng.framework.helper;

import org.hupeng.framework.Keys;
import org.hupeng.framework.util.AntPathMatcher;
import org.hupeng.framework.util.PropsUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

/**
 * @author hupeng
 * @since 2018/7/1.
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

    /**
     * 静态资源判断
     * @param request HttpServletRequest
     * @return isStatic
     */
    public static boolean isStatic(final HttpServletRequest request) {
        return AntPathMatcher.match(getPath(), request.getRequestURI());
    }

}
