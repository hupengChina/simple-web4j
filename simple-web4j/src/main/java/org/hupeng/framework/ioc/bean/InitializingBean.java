package org.hupeng.framework.ioc.bean;

public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
