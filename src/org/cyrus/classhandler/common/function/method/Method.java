package org.cyrus.classhandler.common.function.method;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.function.Function;
import org.cyrus.classhandler.common.line.callers.function.MethodCall;

public interface Method <T extends CommonClass> extends Function<T> {

    boolean isStatic();

    @Override
    default MethodCall getCaller() {
        return new MethodCall(this);
    }

    interface Writable <T extends CommonClass> extends Method<T>, Function.Writable<T>, Function.Namable<T>, Function.Visable<T> {

        Method.Writable setStatic(boolean tatic);
        Method.Writable setReturn(CommonClass class1);

    }
}
