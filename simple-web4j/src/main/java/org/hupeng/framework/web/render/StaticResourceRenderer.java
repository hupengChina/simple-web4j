package org.hupeng.framework.web.render;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.hupeng.framework.web.handler.HandleResult;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;
import org.hupeng.framework.web.server.netty.http.WebNettyRequest;
import org.hupeng.framework.web.server.netty.http.WebNettyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * @author : hupeng
 * @date : 2020/8/4
 */
public class StaticResourceRenderer implements Renderer {

    private static final Logger log = LoggerFactory.getLogger(StaticResourceRenderer.class);

    @Override
    public void render(WebRequest request, WebResponse response, HandleResult handleResult) {
        String uri = null;
        try {
            uri = URLDecoder.decode(request.uri(),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Object result = handleResult.getResult();
        List<String> locationValues = null;

        if(result != null){
           locationValues = (List<String>) result;
        }

        if(locationValues != null){
            byte[] bytes = null;
            for (String locationValue:locationValues) {
                String path = null;
                if(locationValue.startsWith("classpath:")){
                    path = StaticResourceRenderer.class.getResource("/").getPath();
                    if (StringUtils.contains(path, "/target/classes/")) {
                        path = StringUtils.replace(path, "/target/classes/", "/src/main/resources");
                    }
                    path = path.substring(1) + locationValue.replaceFirst("classpath:","") + uri;

                }else if(locationValue.startsWith("file:")){
                    path = locationValue.replaceFirst("file:","") + uri;
                }
                if (path != null) {
                    File file = new File(path);
                    if(file.exists()) {
                        try {
                            bytes = FileUtils.readFileToByteArray(file);
                        } catch (IOException e) {
                            log.error(e.getMessage(),e);
                        }
                        if(bytes != null){
                            response.sendByte(bytes);
                            return;
                        }
                    }
                }
            }
        }
        response.sendError(404);
    }

}
