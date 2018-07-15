package org.hupeng.framework.ioc;

import org.hupeng.framework.ioc.Annotated.AnnotatedType;
import org.hupeng.framework.ioc.bean.Bean;
import org.hupeng.framework.ioc.context.Contextual;
import org.hupeng.framework.ioc.context.CreationalContext;
import org.hupeng.framework.ioc.point.InjectionPoint;
import org.hupeng.framework.ioc.point.InjectionTarget;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * @author hupeng
 * @since 2018/7/15.
 */
public class SimpleBeanManagerImpl implements BeanManager{
    @Override
    public Object getReference(Bean<?> bean, Type beanType, CreationalContext<?> ctx) {
        return null;
    }

    @Override
    public Object getInjectableReference(InjectionPoint ij, CreationalContext<?> ctx) {
        return null;
    }

    @Override
    public <T> CreationalContext<T> createCreationalContext(Contextual<T> contextual) {
        return null;
    }

    @Override
    public Set<Bean<?>> getBeans(Type beanType, Annotation... qualifiers) {
        return null;
    }

    @Override
    public Set<Bean<?>> getBeans(String name) {
        return null;
    }

    @Override
    public Bean<?> getPassivationCapableBean(String id) {
        return null;
    }

    @Override
    public <X> Bean<? extends X> resolve(Set<Bean<? extends X>> beans) {
        return null;
    }

    @Override
    public void validate(InjectionPoint injectionPoint) {

    }

    @Override
    public void fireEvent(Object event, Annotation... qualifiers) {

    }

    @Override
    public boolean isScope(Class<? extends Annotation> annotationType) {
        return false;
    }

    @Override
    public boolean isNormalScope(Class<? extends Annotation> annotationType) {
        return false;
    }

    @Override
    public boolean isPassivatingScope(Class<? extends Annotation> annotationType) {
        return false;
    }

    @Override
    public boolean isQualifier(Class<? extends Annotation> annotationType) {
        return false;
    }

    @Override
    public boolean isInterceptorBinding(Class<? extends Annotation> annotationType) {
        return false;
    }

    @Override
    public boolean isStereotype(Class<? extends Annotation> annotationType) {
        return false;
    }

    @Override
    public Set<Annotation> getInterceptorBindingDefinition(Class<? extends Annotation> bindingType) {
        return null;
    }

    @Override
    public Set<Annotation> getStereotypeDefinition(Class<? extends Annotation> stereotype) {
        return null;
    }

    @Override
    public Context getContext(Class<? extends Annotation> scopeType) {
        return null;
    }

    @Override
    public <T> AnnotatedType<T> createAnnotatedType(Class<T> type) {
        return null;
    }

    @Override
    public <T> InjectionTarget<T> createInjectionTarget(AnnotatedType<T> type) {
        return null;
    }
}
