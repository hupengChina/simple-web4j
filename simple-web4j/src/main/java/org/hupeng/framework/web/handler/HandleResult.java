package org.hupeng.framework.web.handler;

import lombok.Data;
import org.hupeng.framework.web.render.Renderer;

/**
 * @author : hupeng
 * @date : 2020/8/4 16:00
 */
@Data
public class HandleResult {

    Object result;

    Renderer renderer;

}
