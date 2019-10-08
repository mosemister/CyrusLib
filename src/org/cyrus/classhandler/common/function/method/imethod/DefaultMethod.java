package org.cyrus.classhandler.common.function.method.imethod;

import org.cyrus.classhandler.common.classtype.InterfaceClass;
import org.cyrus.classhandler.common.function.method.Method;

/**
 * base class for all interface methods labelled default
 * @param <C> the attached class
 */
public interface DefaultMethod<C extends InterfaceClass> extends Method.Writable<C>, InterfaceMethod<C> {
}
