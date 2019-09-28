package org.cyrus.classhandler.common.line.callers.variable;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Caller;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.variable.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VariableCaller implements Caller {

    protected Store variable;

    public VariableCaller(Store variable){
        this.variable = variable;
    }

    @Override
    public Store getCallable() {
        return this.variable;
    }

    @Override
    public boolean isWrittenCorrectly() {
        if(!(this.variable instanceof Store.Attachable)) {
            return true;
        }
        Optional<Line> opLine = ((Store.Attachable) this.variable).getAttachedLine();
        if(!opLine.isPresent()){
            return true;
        }
        return opLine.get().isWrittenCorrectly();
    }

    @Override
    public Optional<String> getDescriptionOfError() {
        if(!(this.variable instanceof Store.Attachable)) {
            return Optional.empty();
        }
        Optional<Line> opLine = ((Store.Attachable) this.variable).getAttachedLine();
        if(!opLine.isPresent()){
            return Optional.empty();
        }
        return opLine.get().getDescriptionOfError();
    }

    @Override
    public CommonClass getAttachedClass() {
        return this.getAttachedClass();
    }

    @Override
    public List<CommonClass<? extends CommonClass>> getImports() {
        return new ArrayList<>();
    }

    @Override
    public String getAsJavaLine() {
        String line = (this.variable.isFinal() ? "final ": "") + this.variable.getReturn().get().getName() + " " + this.variable.getName();
        if(this.variable instanceof Store.Attachable){
            Optional<Line> opLine = ((Store.Attachable) this.variable).getAttachedLine();
            if(opLine.isPresent()){
                line += " = " + opLine.get().getAsJavaLine();
            }
        }
        return line;
    }

}
