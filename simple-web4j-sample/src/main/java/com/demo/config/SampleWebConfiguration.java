package com.demo.config;

import org.hupeng.framework.ioc.Annotated.Configuration;
import org.hupeng.framework.web.config.WebConfigurer;
import org.hupeng.framework.web.registry.ResourceHandlerRegistry;

@Configuration
public class SampleWebConfiguration implements WebConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static");
        registry.addResourceHandler("/local/**").addResourceLocations("file:C:/Users/Public/Pictures/Sample Pictures");
    }
}
