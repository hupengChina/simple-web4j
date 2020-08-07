package org.hupeng.framework.web.registry;

import com.sun.istack.internal.NotNull;
import org.hupeng.framework.web.handler.ResourceHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : hupeng
 * @date : 2020/8/6
 */
public class ResourceHandlerRegistration {

    private final String[] pathPatterns;

    private final List<String> locationValues = new ArrayList<>();

    public ResourceHandlerRegistration(String... pathPatterns) {
        this.pathPatterns = pathPatterns;
    }

    protected String[] getPathPatterns() {
        return this.pathPatterns;
    }

    public ResourceHandlerRegistration addResourceLocations(String... resourceLocations) {
        this.locationValues.addAll(Arrays.asList(resourceLocations));
        return this;
    }

    @NotNull
    protected ResourceHandler getRequestHandler() {
        ResourceHandler handler = new ResourceHandler();
        handler.setLocationValues(this.locationValues);
        return handler;
    }

}
