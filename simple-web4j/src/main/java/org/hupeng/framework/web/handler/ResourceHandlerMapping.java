package org.hupeng.framework.web.handler;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.common.util.AntPathMatcher;
import org.hupeng.framework.web.server.http.WebRequest;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : hupeng
 * @date : 2020/8/4
 */
public class ResourceHandlerMapping extends AbstractHandlerMapping {

    private Map<String, HttpRequestHandler> urlMap = new LinkedHashMap<>();

    public void setUrlMap(Map<String, HttpRequestHandler> urlMap){
        this.urlMap = urlMap;
    }

    @Nullable
    @Override
    public Object getHandlerInternal(WebRequest webRequest) {
        String uri = webRequest.getFullHttpRequest().uri();
        for (Map.Entry<String, HttpRequestHandler> entry : urlMap.entrySet()) {
            String url = entry.getKey();
            HttpRequestHandler resourceHandler = entry.getValue();
            if (AntPathMatcher.match(url, uri)) {
                return resourceHandler;
            }
        }
        return null;
    }
}
