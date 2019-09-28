package org.cyrus.classhandler.common.line.callers.function;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.function.method.Method;

public class MethodCall <C extends CommonClass> extends FunctionCall<C> {

    public MethodCall(Method<C> function) {
        super(function);
    }

    @Override
    public String getAsJavaLine() {
        return null;
    }
}
