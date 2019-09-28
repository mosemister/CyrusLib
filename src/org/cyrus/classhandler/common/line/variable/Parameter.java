package org.cyrus.classhandler.common.line.variable;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.function.Function;

public interface Parameter<C extends CommonClass> extends Store {

    Function<C> getAttachedFunction();

    @Override
    default CommonClass getAttachedClass(){
        return getAttachedFunction().getAttachedClass();
    }

    interface Writable<C extends CommonClass> extends Store.Writable, Parameter<C>{

        Parameter.Writable<C> setReturn(CommonClass<? extends CommonClass> class1);

    }

}
