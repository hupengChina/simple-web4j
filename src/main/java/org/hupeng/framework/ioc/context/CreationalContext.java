package org.hupeng.framework.ioc.context;

/**
 * <p>Provides operations that are used by the
 * {@link org.hupeng.framework.ioc.context.Contextual
 * javax.enterprise.context.spi.Contextual} implementation during
 * instance creation and destruction.</p>
 *
 * @author Gavin King
 * @author Pete Muir
 */
public interface CreationalContext<T> {

    /**
     * Registers an incompletely initialized contextual instance the with the
     * container. A contextual instance is considered incompletely initialized
     * until it is returned by
     * {@link org.hupeng.framework.ioc.context.Contextual#create(CreationalContext)
     * javax.enterprise.context.spi.Contextual#create(CreationalContext)}.
     *
     * @param incompleteInstance the incompletely initialized instance
     */
    void push(T incompleteInstance);

    /**
     * Destroys all dependent objects of the instance which is being destroyed,
     * by passing each dependent object to
     * {@link org.hupeng.framework.ioc.context.Contextual#create(CreationalContext)
     * javax.enterprise.context.spi.Contextual#destroy(Object, CreationalContext)}.
     */
    void release();

}
