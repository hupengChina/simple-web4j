package org.hupeng.framework.web.registry;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.ioc.support.WebApplicationContext;
import org.hupeng.framework.web.config.WebApplicationInitializer;
import org.hupeng.framework.web.handler.HandlerMapping;
import org.hupeng.framework.web.handler.HttpRequestHandler;
import org.hupeng.framework.web.handler.ResourceHandler;
import org.hupeng.framework.web.handler.ResourceHandlerMapping;

import java.util.*;

/**
 * @author : hupeng
 * @date : 2020/8/6
 */
public class ResourceHandlerRegistry {


    private final List<ResourceHandlerRegistration> registrations = new ArrayList<>();

    public ResourceHandlerRegistration addResourceHandler(String... pathPatterns) {
        ResourceHandlerRegistration registration = new ResourceHandlerRegistration(pathPatterns);
        this.registrations.add(registration);
        return registration;
    }

    @Nullable
    public HandlerMapping getHandlerMapping() {
        if (this.registrations.isEmpty()) {
            return null;
        }
        Map<String, HttpRequestHandler> urlMap = new LinkedHashMap<>();
        for (ResourceHandlerRegistration registration : this.registrations) {
            for (String pathPattern : registration.getPathPatterns()) {
                ResourceHandler handler = registration.getRequestHandler();
                urlMap.put(pathPattern, handler);
            }
        }
        ResourceHandlerMapping handlerMapping = new ResourceHandlerMapping();
        handlerMapping.setUrlMap(urlMap);
        return handlerMapping;
    }
}
