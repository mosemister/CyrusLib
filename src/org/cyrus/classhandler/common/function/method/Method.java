package org.cyrus.classhandler.common.function.method;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.function.Function;
import org.cyrus.classhandler.common.line.callers.function.MethodCall;

/**
 * Base class for all methods
 * @param <T> the attached class
 */
public interface Method <T extends CommonClass> extends Function<T> {

    /**
     * checks if the method is static or not
     * @return if the method is static
     */
    boolean isStatic();

    /**
     * creates a new caller for this method
     * @return a new instanceof a caller for this method
     */
    @Override
    default MethodCall createCaller(CommonClass<? extends CommonClass> clazz) {
        return new MethodCall(clazz, this);
    }

    /**
     * base class for writable methods
     * @param <T> the extending class
     */
    interface Writable <T extends CommonClass> extends Method<T>, Function.Writable<T>, Nameable<T>, Visible<T> {

        /**
         * Sets the method to static
         * @param tatic if the method should be static
         * @return itself for chaining
         */
        Method.Writable setStatic(boolean tatic);

        /**
         * Sets the return of the method
         * @param class1 the class to return. null if void
         * @return itself for chaining
         */
        Method.Writable setReturn(CommonClass class1);

    }
}
