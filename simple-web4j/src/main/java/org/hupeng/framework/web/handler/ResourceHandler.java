package org.hupeng.framework.web.handler;

import org.apache.tika.Tika;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

import java.util.List;

/**
 * @author : hupeng
 * @date : 2020/8/4
 */
public class ResourceHandler implements Handler,HttpRequestHandler{

    private static final Tika TIKA = new Tika();

    private List<String> locationValues;

    public void setLocationValues(List<String> locationValues){
        this.locationValues = locationValues;
    }

    public List<String> getLocationValues(){
        return locationValues;
    }

    @Override
    public void handleRequest(WebRequest request, WebResponse response){
        final String contentType = TIKA.detect(request.getFullHttpRequest().uri());
        response.setHeader("content-type", contentType);
    }


}
