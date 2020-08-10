package org.hupeng.framework.web.handler;

import org.hupeng.framework.util.AntPathMatcher;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author : hupeng
 * @date : 2020/8/4
 */
public class ResourceHandlerMapping implements HandlerMapping {

    private Map<String, HttpRequestHandler> urlMap = new LinkedHashMap<>();

    public void setUrlMap(Map<String, HttpRequestHandler> urlMap){
        this.urlMap = urlMap;
    }

    @Override
    public Object getHandler(String requestPath) {
        for (Map.Entry<String, HttpRequestHandler> entry : urlMap.entrySet()) {
            String url = entry.getKey();
            HttpRequestHandler resourceHandler = entry.getValue();
            if (AntPathMatcher.match(url, requestPath)) {
                return resourceHandler;
            }
        }
        return null;
    }
}
