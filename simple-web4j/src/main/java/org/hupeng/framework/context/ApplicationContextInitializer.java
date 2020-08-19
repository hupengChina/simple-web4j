package org.hupeng.framework.context;

/**
 * @author : hupeng
 * @date : 2020/8/19
 */
public interface ApplicationContextInitializer<C extends ConfigurableApplicationContext> {

    void initialize(C applicationContext);

}
