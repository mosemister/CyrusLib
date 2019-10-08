package org.cyrus.classhandler.common.line.callers.function;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.function.method.Method;

/**
 * The caller for Methods
 * @param <C> The attached class
 */
public class MethodCall <M extends Method<? extends CommonClass>, C extends CommonClass> extends FunctionCall<M, C> {

    public MethodCall(C clazz, M function) {
        super(clazz, function);
    }
}
