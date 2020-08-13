package org.hupeng.framework.web.handler;

import com.sun.istack.internal.Nullable;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.hupeng.framework.ioc.bean.InitializingBean;
import org.hupeng.framework.web.annotated.RequestMapping;
import org.hupeng.framework.web.server.http.WebRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : hupeng
 * @date : 2020/8/12
 */
public abstract class AbstractHandlerMethodMapping<T> extends AbstractHandlerMapping implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(AbstractHandlerMethodMapping.class);

    private final MappingRegistry mappingRegistry = new MappingRegistry();

    @Override
    public void afterPropertiesSet() {
        initHandlerMethods();
    }

    protected void initHandlerMethods()  {
        Collection<Class<?>> classes = obtainApplicationContext().getClasses();
        for (Class beanType: classes) {
            if (isHandler(beanType)) {
                Object handler = obtainApplicationContext().getBean(beanType);
                detectHandlerMethods(handler);
            }
        }
    }

    protected void detectHandlerMethods(Object handler) {
        Class<?> handlerType = handler.getClass();
        if (handlerType != null) {
            Map<Method, T> methodMap = new LinkedHashMap<>();
            Method[] methods = handlerType.getMethods();
            for (Method method: methods) {
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                if(requestMapping != null){
                    methodMap.put(method,(T) getMappingForMethod(method,handlerType));
                }
            }
            methodMap.forEach((method, mapping) -> {
                registerHandlerMethod(handler, method, mapping);
            });
        }
    }

    protected void registerHandlerMethod(Object handler, Method method, T mapping) {
        this.mappingRegistry.register(mapping, handler, method);
    }

    @Override
    protected Object getHandlerInternal(WebRequest request) {
        String lookupPath = request.getFullHttpRequest().uri();
        this.mappingRegistry.acquireReadLock();
        try {
            HandlerMethod handlerMethod = lookupHandlerMethod(lookupPath, request);
            return handlerMethod;
        }
        finally {
            this.mappingRegistry.releaseReadLock();
        }
    }

    @Nullable
    protected HandlerMethod lookupHandlerMethod(String lookupPath, WebRequest request){
        Collection<T> directPathMatches = this.mappingRegistry.getMappingsByUrl(lookupPath);
        if(directPathMatches != null){
            for (T mapping: directPathMatches) {
                if (matchingMapping(mapping, request)) {
                    return this.mappingRegistry.getMappings().get(mapping);
                }
            }
        }
        return null;
    }

    protected abstract boolean isHandler(Class<?> beanType);

    @Nullable
    protected abstract T getMappingForMethod(Method method, Class<?> handlerType);

    @Nullable
    protected abstract boolean matchingMapping(T mapping, WebRequest request);

    protected abstract String getMappingPathPattern(T mapping);

    class MappingRegistry {

        private final Map<T, HandlerMethod> mappingLookup = new LinkedHashMap<>();

        private final MultiValuedMap<String, T> urlLookup = new ArrayListValuedHashMap<>();

        private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        public void register(T mapping, Object handler, Method method) {
            this.readWriteLock.writeLock().lock();
            try {
                this.mappingLookup.put(mapping, new HandlerMethod(handler, method));
                this.urlLookup.put(getMappingPathPattern(mapping), mapping);
            } finally {
                this.readWriteLock.writeLock().unlock();
            }
        }

        public Map<T, HandlerMethod> getMappings() {
            return this.mappingLookup;
        }

        @Nullable
        public Collection<T> getMappingsByUrl(String urlPath) {
            return this.urlLookup.get(urlPath);
        }

        public void acquireReadLock() {
            this.readWriteLock.readLock().lock();
        }

        public void releaseReadLock() {
            this.readWriteLock.readLock().unlock();
        }

    }

}
