package org.cyrus.classhandler.common.line.callers.array;

import org.cyrus.classhandler.common.classtype.ArrayClass;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Caller;
import org.cyrus.classhandler.common.line.Writable;

public abstract class ArrayConstructorCall<C extends CommonClass> implements Caller<ArrayClass<? extends ArrayClass, ? extends CommonClass>, C>, Writable {

    protected ArrayClass<? extends ArrayClass, ? extends CommonClass> clazz;
    protected C attached;

    public ArrayConstructorCall(C attached, ArrayClass<? extends ArrayClass, ? extends CommonClass> call){
        this.clazz = call;
        this.attached = attached;
    }

    @Override
    public ArrayClass<? extends ArrayClass, ? extends CommonClass> getCallable() {
        return this.clazz;
    }

    @Override
    public CommonClass<C> getAttachedClass() {
        return this.attached;
    }
}
