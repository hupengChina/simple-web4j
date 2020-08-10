package org.hupeng.framework.web.render;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.hupeng.framework.web.handler.HandleResult;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author : hupeng
 * @date : 2020/8/4
 */
public class StaticResourceRenderer implements Renderer {

    @Override
    public void render(WebRequest request, WebResponse response, HandleResult handleResult) {
        String uri = request.getFullHttpRequest().uri();

        byte[] bytes = null;

        List<String> locationValues = null;
        Object result = handleResult.getResult();
        if(result != null){
           locationValues = (List<String>) result;
        }
        if(locationValues != null){
            for (String locationValue:locationValues) {
                if(locationValue.startsWith("classpath:")){
                    String path = StaticResourceRenderer.class.getResource("/").getPath();
                    if (StringUtils.contains(path, "/target/classes/")) {
                        path = StringUtils.replace(path, "/target/classes/", "/src/main/resources");
                    }
                    path = path.substring(1,path.length()) + locationValue.replace("classpath:","") + uri;
                    bytes = new byte[0];
                    try {
                        bytes = FileUtils.readFileToByteArray(new File(path));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(bytes != null){
                    response.sendByte(bytes);
                    return;
                }
            }
        }
        if(bytes == null){
            response.sendError(404);
        }
    }



}
