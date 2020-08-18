package org.hupeng.framework.ioc.support;

import org.hupeng.framework.ioc.AbstractApplicationContext;

/**
 * @author : hupeng
 * @date : 2020/8/13
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(AbstractApplicationContext applicationContext);

}
