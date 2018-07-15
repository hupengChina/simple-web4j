package org.hupeng.framework.ioc;

/**
 * @author hupeng
 * @since 2018/7/15.
 */
public class Lifecycle {

    private static BeanManager beanManager;

    public static void setBeanManager(BeanManager beanManager) {
        Lifecycle.beanManager = beanManager;
    }

    public static BeanManager getBeanManager() {
        return beanManager;
    }
}
