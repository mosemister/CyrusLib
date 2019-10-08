package org.cyrus.classhandler.common.line;

import org.cyrus.classhandler.common.classtype.CommonClass;

import java.util.Optional;

public interface Caller<C extends Callable, X extends CommonClass> extends Line<X> {

    C getCallable();

    @Override
    default Optional<CommonClass<? extends CommonClass>> getReturn(){
        return this.getCallable().getReturn();
    }
}
