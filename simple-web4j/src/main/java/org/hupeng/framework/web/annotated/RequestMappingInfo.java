package org.hupeng.framework.web.annotated;

import com.sun.istack.internal.Nullable;
import io.netty.handler.codec.http.HttpMethod;
import org.hupeng.framework.web.server.http.WebRequest;

/**
 * @author : hupeng
 * @date : 2020/8/12
 */
public class RequestMappingInfo {

    String value;

    public String getValue() {
        return value;
    }

    public RequestMappingInfo setValue(String value) {
        value = value;
        return this;
    }

    @Nullable
    public boolean matchingCondition(WebRequest request) {//todo

        String uri = request.getFullHttpRequest().uri();
        HttpMethod method = request.getFullHttpRequest().method();

        return value.equals(uri);
    }
}
