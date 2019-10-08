package org.cyrus.classhandler.common.line.callers.classcall;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Caller;
import org.cyrus.classhandler.common.line.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ThisCall<T extends CommonClass> implements Caller<T, T>, Writable {

    T attached;

    public ThisCall(T clazz){
        this.attached = clazz;
    }

    @Override
    public T getCallable() {
        return this.attached;
    }

    @Override
    public boolean isWrittenCorrectly() {
        return true;
    }

    @Override
    public Optional<String> getDescriptionOfError() {
        return Optional.empty();
    }

    @Override
    public CommonClass<T> getAttachedClass() {
        return this.attached;
    }

    @Override
    public List<CommonClass<? extends CommonClass>> getImports() {
        return new ArrayList<>();
    }

    @Override
    public String getAsJavaLine() {
        return "this";
    }
}
