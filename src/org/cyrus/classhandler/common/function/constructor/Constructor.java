package org.cyrus.classhandler.common.function.constructor;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.function.Function;
import org.cyrus.classhandler.common.line.callers.function.ConstructorCall;

import java.util.Optional;

/**
 * base class for all constructors
 * @param <C> the attached class
 */
public interface Constructor<C extends CommonClass> extends Function<C> {

    /**
     * Creates a new constructor call for this constructor
     * @return a new  caller for this constructor
     */
    @Override
    default ConstructorCall<C> createCaller(CommonClass<? extends CommonClass> clazz) {
        return new ConstructorCall(clazz, this);
    }

    /**
     * The name of the constructor is the name of the class
     * @return the name of the class
     */
    @Override
    default String getName() {
        return getAttachedClass().getName();
    }

    /**
     * The return of the constructor is the attached class
     * @return the attached class in a optional
     */
    @Override
    default Optional<CommonClass<? extends CommonClass>> getReturn() {
        return Optional.of(getAttachedClass());
    }

    interface Writable<C extends CommonClass> extends Constructor<C>, Function.Writable<C> {

    }
}
