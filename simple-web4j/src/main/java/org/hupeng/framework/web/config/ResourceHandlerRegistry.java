package org.hupeng.framework.web.config;

import org.hupeng.framework.web.handler.HandlerMapping;
import org.hupeng.framework.web.handler.HttpRequestHandler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

}
