package org.hupeng.framework.ioc.context;

/**
 * <p>Defines operations to create and destroy contextual instances of a
 * certain type. Any implementation of {@code Contextual} is called a
 * contextual type. In particular, all beans are contextual types.</p>
 *
 * @see org.hupeng.framework.ioc.bean.Bean javax.enterprise.inject.spi.Bean
 *
 * @author Gavin King
 * @author Nicklas Karlsson
 * @author Pete Muir
 */
public interface Contextual<T>
{
    /**
     * Create a new instance of the contextual type. Instances should
     * use the given {@link CreationalContext}
     * when obtaining contextual references to inject, in order to ensure
     * that any dependent objects are associated with the contextual instance
     * that is being created. An implementation may call
     * {@link CreationalContext#push(Object)}
     * between instantiation and injection to help the container minimize the
     * use of client proxy objects.
     *
     * @param creationalContext
     *            the context in which this instance is being created
     * @return the contextual instance
     *            if a checked exception occurs while creating the instance
     */
    T create(CreationalContext<T> creationalContext);

    /**
     * Destroy an instance of the contextual type. Implementations should
     * call {@link CreationalContext#release()}
     * to allow the container to destroy dependent objects of the contextual
     * instance.
     *
     * @param instance
     *           the contextual instance to destroy
     * @param creationalContext
     *           the context in which this instance was created
     */
    void destroy(T instance, CreationalContext<T> creationalContext);
}