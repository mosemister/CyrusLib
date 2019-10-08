package org.cyrus.classhandler.common.function.method.cmethod;

import org.cyrus.classhandler.common.classtype.StandardClass;
import org.cyrus.classhandler.common.function.method.Method;

/**
 * base class for all methods that go inside a standard class
 * @param <C> the attached class
 */
public interface ClassMethod<C extends StandardClass> extends Method.Writable<C> {
}
