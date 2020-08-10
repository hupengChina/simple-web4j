package org.hupeng.framework.web.config;

import org.hupeng.framework.web.registry.ResourceHandlerRegistry;

public interface WebConfigurer {

    default void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

}
