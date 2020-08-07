package org.hupeng.framework.ioc.support;

import java.util.Collection;

public interface WebApplicationContext {

    Collection<Class<?>> getClasses();
}
