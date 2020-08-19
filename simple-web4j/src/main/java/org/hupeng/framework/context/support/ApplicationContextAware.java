package org.hupeng.framework.context.support;

import org.hupeng.framework.context.AbstractApplicationContext;

/**
 * @author : hupeng
 * @date : 2020/8/13
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(AbstractApplicationContext applicationContext);

}
