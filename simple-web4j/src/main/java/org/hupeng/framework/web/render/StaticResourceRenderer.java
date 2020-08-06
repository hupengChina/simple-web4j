package org.hupeng.framework.web.render;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.hupeng.framework.web.handler.HandleResult;
import org.hupeng.framework.web.server.http.WebRequest;
import org.hupeng.framework.web.server.http.WebResponse;

import java.io.File;
import java.io.IOException;

/**
 * @author : hupeng
 * @date : 2020/8/4
 */
public class StaticResourceRenderer implements Renderer {

    @Override
    public void render(WebRequest request, WebResponse response, HandleResult handleResult) {

        String uri = request.getFullHttpRequest().uri();
        String path = StaticResourceRenderer.class.getResource("/").getPath();
        if (StringUtils.contains(path, "/target/classes/") || StringUtils.contains(path, "/target/test-classes/")) {
            path = StringUtils.replace(path, "/target/classes/", "/src/main/resources");
            path = StringUtils.replace(path, "/target/test-classes/", "/src/main/resources");
        }
        path += uri;
        byte[] bytes = new byte[0];
        try {
            path = path.substring(1,path.length());
            bytes = FileUtils.readFileToByteArray(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        response.sendByte(bytes);
    }

}
