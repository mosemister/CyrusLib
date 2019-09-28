package org.cyrus.classhandler.common.line.callers;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Callable;
import org.cyrus.classhandler.common.line.Caller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StaticClassCall implements Caller {

    protected CommonClass class1;

    public StaticClassCall(CommonClass class1){
        this.class1 = class1;
    }

    @Override
    public String getAsJavaLine() {
        return this.class1.getName();
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
        return this.class1;
    }

    @Override
    public List<CommonClass<? extends CommonClass>> getImports() {
        List<CommonClass<? extends CommonClass>> list = new ArrayList<>();
        list.add(this.class1);
        return list;
    }

    @Override
    public Optional<CommonClass<? extends CommonClass>> getReturn() {
        return Optional.of(this.class1);
    }

    @Override
    public Callable getCallable() {
        return this.class1;
    }
}
