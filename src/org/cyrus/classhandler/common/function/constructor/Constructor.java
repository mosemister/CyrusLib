package org.cyrus.classhandler.common.function.constructor;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.function.Function;
import org.cyrus.classhandler.common.line.callers.function.ConstructorCall;

public interface Constructor<C extends CommonClass> extends Function<C> {

    @Override
    default ConstructorCall<C> getCaller() {
        return new ConstructorCall(this);
    }
}
