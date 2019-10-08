package org.cyrus.classhandler.common.function;

import org.cyrus.classhandler.common.appliable.AnnotationApplicable;
import org.cyrus.classhandler.common.appliable.GenericApplicable;
import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Callable;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.statement.Statement;
import org.cyrus.classhandler.common.line.variable.Parameter;

import java.util.List;

/**
 * base class for all functions
 * @param <C> the attached class
 */
public interface Function <C extends CommonClass> extends Callable, GenericApplicable, AnnotationApplicable<C> {

    /**
     * gets the attached attached class
     * @return the attached class
     */
    C getAttachedClass();

    Visibility getVisibility();

    List<Parameter<? extends CommonClass>> getParameters();

    /**
     * base class for writable functions
     * @param <T>
     */
    interface Writable <T extends CommonClass> extends Function<T>, Statement {

    }

    /**
     * base class for all writable nameable functions
     * @param <C> the attached class
     */
    interface Nameable<C extends CommonClass> extends Function<C> {

        /**
         * @param name the new name of the function
         * @return itself for chaining
         */
        Nameable<C> setName(String name);

    }

    /**
     * base class for writable functions that have visibility
     * @param <C> the attached class
     */
    interface Visible<C extends CommonClass> extends Function<C> {

        /**
         * Sets the visibility of the class
         * @param vis the new visibility
         * @return itself for chaining
         */
        Visible<C> setVisibility(Visibility vis);

    }

}
