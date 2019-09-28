package org.cyrus.classhandler.common.line.callers.classcall;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Callable;
import org.cyrus.classhandler.common.line.Caller;
import org.cyrus.classhandler.java.classtype.CommonJavaClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NewNumberCall<N extends Number> implements Caller {

    protected N value;

    public NewNumberCall(){
        this.value = null;
    }

    public NewNumberCall(Class<N> class1){
        this();
    }

    public NewNumberCall(N number){
        this.value = number;
    }

    public N getNumber(){
        return value;
    }

    @Override
    public Callable getCallable() {
        return getAttachedClass();
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
        return CommonJavaClass.of(this.value.getClass());
    }

    @Override
    public List<CommonClass<? extends CommonClass>> getImports() {
        return new ArrayList<>();
    }

    @Override
    public String getAsJavaLine() {
        if(this.value == null){
            return "";
        }
        return this.value.toString();
    }
}
