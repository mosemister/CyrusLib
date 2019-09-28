package org.cyrus.classhandler.common.line.callers.function;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.function.constructor.Constructor;

public class ConstructorCall<C extends CommonClass> extends FunctionCall<C> {

    public ConstructorCall(Constructor<C> function) {
        super(function);
    }

    @Override
    public String getAsJavaLine() {
        return null;
    }
}
