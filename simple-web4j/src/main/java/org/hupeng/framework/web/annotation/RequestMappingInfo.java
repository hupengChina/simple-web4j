package org.hupeng.framework.web.annotation;

import com.sun.istack.internal.Nullable;
import org.hupeng.framework.web.server.http.WebRequest;

/**
 * @author : hupeng
 * @date : 2020/8/12
 */
public class RequestMappingInfo {

    String value;

    String method;

    public String getValue() {
        return value;
    }

    public RequestMappingInfo setValue(String value) {
        this.value = value;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public RequestMappingInfo setMethod(String method) {
        this.method = method;
        return this;
    }

    @Nullable
    public boolean matchingCondition(WebRequest request) {//todo

        String uri = request.uri();
        String method = request.method();

        return this.value.equals(uri);
    }
}
