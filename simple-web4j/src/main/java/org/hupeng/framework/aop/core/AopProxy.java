package org.hupeng.framework.aop.core;

import com.sun.istack.internal.Nullable;

public interface AopProxy {

    Object getProxy();

    Object getProxy(@Nullable ClassLoader classLoader);

}
