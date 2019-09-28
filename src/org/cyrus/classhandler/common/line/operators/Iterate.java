package org.cyrus.classhandler.common.line.operators;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.classtype.StandardClass;
import org.cyrus.classhandler.common.line.Caller;
import org.cyrus.classhandler.common.line.callers.variable.VariableCaller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Iterate implements Operator {

    protected boolean increase;
    protected List<Caller> attached = new ArrayList<>();

    public Iterate(boolean increase, VariableCaller store){
        this.increase = increase;
        this.attached.add(store);
    }

    public boolean isIncreasing(){
        return this.increase;
    }

    @Override
    public boolean isWrittenCorrectly() {
        if(this.attached.size() != 1){
            return false;
        }
        if(!(this.attached.get(0).getReturn().get().isInstanceOf(null))){//check number
            return false;
        }
        if(!(this.attached.get(0).getReturn().get() instanceof StandardClass.PrimitiveClass)){
            return false;
        }
        return true;
    }

    @Override
    public Optional<String> getDescriptionOfError() {
        if(this.attached.size() != 1){
            return Optional.of("Iterate operator can only have 1 value");
        }
        if(!(this.attached.get(0).getReturn().get().isInstanceOf(null))){//check number
            return Optional.of("Attached value must be a primitive number");
        }
        if(!(this.attached.get(0).getReturn().get() instanceof StandardClass.PrimitiveClass)){
            return Optional.of("Attached value must be a primitive number");
        }
        return Optional.empty();
    }

    @Override
    public String getAsJavaLine() {
        if(increase){
            return this.attached.get(0).getCallable().getName() + "++";
        }
        return this.attached.get(0).getCallable().getName() + "--";
    }

    @Override
    public Optional<CommonClass<? extends CommonClass>> getReturn() {
        return Optional.empty();
    }

    @Override
    public List<Caller> getAttached() {
        return this.attached;
    }

    @Override
    public int getMaxAttachments() {
        return 1;
    }
}
