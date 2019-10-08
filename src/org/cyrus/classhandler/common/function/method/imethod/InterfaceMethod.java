package org.cyrus.classhandler.common.function.method.imethod;

import org.cyrus.classhandler.common.classtype.InterfaceClass;
import org.cyrus.classhandler.common.function.method.Method;

/**
 * base class for all methods in interface
 * @param <C> the attached class
 */
public interface InterfaceMethod <C extends InterfaceClass> extends Method<C> {
}
