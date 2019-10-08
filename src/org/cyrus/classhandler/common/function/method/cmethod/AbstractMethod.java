package org.cyrus.classhandler.common.function.method.cmethod;

import org.cyrus.classhandler.common.classtype.StandardClass;

/**
 * Base class for all methods that are abstract
 * @param <C> the attached class
 */
public interface AbstractMethod<C extends StandardClass> extends ClassMethod<C> {
}
