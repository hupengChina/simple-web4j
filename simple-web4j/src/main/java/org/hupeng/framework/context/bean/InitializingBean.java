package org.hupeng.framework.context.bean;

public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
