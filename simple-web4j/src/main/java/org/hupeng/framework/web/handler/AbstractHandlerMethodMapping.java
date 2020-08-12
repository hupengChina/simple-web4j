package org.hupeng.framework.web.handler;

import com.sun.istack.internal.Nullable;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.map.MultiValueMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.hupeng.framework.web.annotated.RequestMappingInfo;
import org.hupeng.framework.web.server.http.WebRequest;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : hupeng
 * @date : 2020/8/12
 */
public abstract class AbstractHandlerMethodMapping<T> extends AbstractHandlerMapping {

    private final MappingRegistry mappingRegistry = new MappingRegistry();


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
            for (T mapping : directPathMatches) {
                if (matchingMapping(mapping, request)) {
                    return this.mappingRegistry.getMappings().get(mapping);
                }
            }
        }
        return null;
    }

    @Nullable
    protected abstract boolean matchingMapping(T mapping, WebRequest request);

    class MappingRegistry {

        private final Map<T, MappingRegistration<T>> registry = new HashMap<>();

        private final Map<T, HandlerMethod> mappingLookup = new LinkedHashMap<>();

        private final MultiValuedMap<String, T> urlLookup = new ArrayListValuedHashMap<>();

        private final Map<String, List<HandlerMethod>> nameLookup = new ConcurrentHashMap<>();

        private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

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

    private static class MappingRegistration<T> {

        private final T mapping;

        private final HandlerMethod handlerMethod;

        private final List<String> directUrls;

        public MappingRegistration(T mapping, HandlerMethod handlerMethod, @Nullable List<String> directUrls) {
            this.mapping = mapping;
            this.handlerMethod = handlerMethod;
            this.directUrls = (directUrls != null ? directUrls : Collections.emptyList());
        }

        public T getMapping() {
            return this.mapping;
        }

        public HandlerMethod getHandlerMethod() {
            return this.handlerMethod;
        }

        public List<String> getDirectUrls() {
            return this.directUrls;
        }

    }

}
