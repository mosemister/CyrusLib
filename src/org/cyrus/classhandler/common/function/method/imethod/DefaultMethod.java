package org.cyrus.classhandler.common.function.method.imethod;

import org.cyrus.classhandler.common.classtype.InterfaceClass;
import org.cyrus.classhandler.common.function.method.Method;

public interface DefaultMethod<C extends InterfaceClass> extends Method.Writable<C>, InterfaceMethod<C> {
}
