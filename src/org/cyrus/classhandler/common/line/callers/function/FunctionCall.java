package org.cyrus.classhandler.common.line.callers.function;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.function.Function;
import org.cyrus.classhandler.common.line.Callable;
import org.cyrus.classhandler.common.line.Caller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class FunctionCall<C extends CommonClass> implements Caller {

    protected Function<C> function;

    public FunctionCall(Function<C> function){
        this.function = function;
    }

    @Override
    public Callable getCallable() {
        return this.function;
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
    public CommonClass getAttachedClass() {
        return this.function.getAttachedClass();
    }

    @Override
    public Optional<CommonClass<? extends CommonClass>> getReturn() {
        return this.function.getReturn();
    }

    @Override
    public List<CommonClass<? extends CommonClass>> getImports(){
        return new ArrayList<>();
    }
}
