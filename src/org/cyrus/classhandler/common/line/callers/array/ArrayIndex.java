package org.cyrus.classhandler.common.line.callers.array;

import org.cyrus.classhandler.common.classtype.ArrayClass;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Caller;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.Writable;
import org.cyrus.classhandler.java.classtype.primitive.ZeroPackageJavaClass;

import java.util.List;
import java.util.Optional;

public class ArrayIndex<C extends CommonClass> implements Caller<ArrayClass<? extends ArrayClass, ? extends CommonClass>, C>, Writable {

    protected Line<? extends CommonClass> callTo;
    protected Line<C> line;

    public ArrayIndex(Line<? extends ArrayClass> callTo, Line<C> line){
        this.callTo = callTo;
        this.line = line;
    }

    @Override
    public ArrayClass<? extends ArrayClass, ? extends CommonClass> getCallable() {
        return (ArrayClass<? extends ArrayClass, ? extends CommonClass>) this.callTo.getReturn().get();
    }

    @Override
    public boolean isWrittenCorrectly() {
        Optional<CommonClass<? extends CommonClass>> ret = this.line.getReturn();
        if(!ret.isPresent()){
            return false;
        }
        if(!this.callTo.getReturn().isPresent()){
            return false;
        }
        if((this.callTo.getReturn().get() instanceof ArrayClass)){
            return false;
        }
        if(ret.get().isMatch(ZeroPackageJavaClass.INTEGER)){
            return true;
        }
        return false;
    }

    @Override
    public Optional<String> getDescriptionOfError() {
        Optional<CommonClass<? extends CommonClass>> ret = this.line.getReturn();
        if(!ret.isPresent()){
            return Optional.of("Index number is not a whole number");
        }
        if(!this.callTo.getReturn().isPresent()){
            return Optional.of("Variable must be an array");
        }
        if((this.callTo.getReturn().get() instanceof ArrayClass)){
            return Optional.of("Variable must be an array");
        }
        if(ret.get().isMatch(ZeroPackageJavaClass.INTEGER)){
            return Optional.empty();
        }
        return Optional.of("Index number is not a whole number");
    }

    @Override
    public CommonClass<C> getAttachedClass() {
        return this.line.getAttachedClass();
    }

    @Override
    public List<CommonClass<? extends CommonClass>> getImports() {
        return this.line.getImports();
    }

    @Override
    public String getAsJavaLine() {
        return this.callTo.getAsJavaLine() + "[" + this.line.getAsJavaLine() + "]";
    }
}
