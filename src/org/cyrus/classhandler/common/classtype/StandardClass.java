package org.cyrus.classhandler.common.classtype;

import org.cyrus.classhandler.common.function.constructor.Constructor;
import org.cyrus.classhandler.common.function.method.Method;
import org.cyrus.classhandler.common.line.Callable;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * base class for all 'standard classes' (class)
 * @param <C> itself
 */
public interface StandardClass<C extends StandardClass> extends CommonClass<C> {

    /**
     * Returns if the class is abstract
     * @return if the class is abstract
     */
    boolean isAbstract();

    /**
     * Gets all constructors of the class
     * @return all the constructors of the class
     */
    List<Constructor<C>> getConstructors();

    /**
     * Gets the extending class
     * @return The extending class
     */
    Optional<StandardClass> getExtends();

    @Override
    default Set<Callable> getCalls() {
        Set<Callable> set = new HashSet<>();
        set.addAll(this.getFields());
        set.addAll(this.getMethods());
        getExtends().ifPresent(e -> set.addAll(e.getCalls()));
        return set;
    }

    /**
     * Base class for writable standard class
     * @param <C> itself
     */
    interface Writable <C extends StandardClass.Writable> extends StandardClass<C>, CommonClass.Writable<C>{

        /**
         * sets the class as abstract or not
         * @param check if the class is abstract
         * @return itself for chaining
         */
        StandardClass.Writable setAbstract(boolean check);

        /**
         * Sets the extending class
         * @param class1 the class to extend from
         * @return itself for chaining
         */
        StandardClass.Writable setExtends(StandardClass class1);

        @Override
        default Optional<String> getDescriptionOfError() {
            Optional<? extends Method<C>> opMethod = getMethods().stream().filter(m -> m instanceof Method.Writable).filter(m -> ((Method.Writable) m).getDescriptionOfError().isPresent()).findAny();
            if(opMethod.isPresent()){
                return ((Method.Writable)opMethod.get()).getDescriptionOfError();
            }
            return CommonClass.Writable.super.getDescriptionOfError();
        }

    }


}
