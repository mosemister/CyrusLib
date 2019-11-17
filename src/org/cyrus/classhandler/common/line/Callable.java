package org.cyrus.classhandler.common.line;

import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.variable.Parameter;

import java.util.List;

/**
 * base class for callable objects.
 *
 * A callable object is a object that can be 'called'
 * within java itself.
 */
public interface Callable extends Returnable {

    /**
     * Gets the name of the callable, this is to identify the callable for a caller
     * @return The name of the callable
     */
    String getName();

    /**
     * If the name of the callable is multiple words, it will be split here
     * @return The split of words from the name
     */
    String[] getSplitName();

    /**
     * @param attachedTo The class that will use the caller
     * @return creates a new caller that this calls to
     */
    Caller createCaller(CommonClass<? extends CommonClass> attachedTo);

    /**
     *
     */
    /*interface StandardCallable extends Callable {

        Visibility getVisibility();

        List<Parameter<? extends CommonClass>> getParameters();
    }*/

}
