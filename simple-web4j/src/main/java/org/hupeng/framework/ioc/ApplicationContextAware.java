package org.hupeng.framework.ioc;

import org.hupeng.framework.ioc.support.WebApplicationContext;

/**
 * @author : hupeng
 * @date : 2020/8/13
 */
public interface ApplicationContextAware extends Aware{

    void setApplicationContext(WebApplicationContext applicationContext);

}
