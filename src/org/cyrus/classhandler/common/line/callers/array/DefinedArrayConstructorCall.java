package org.cyrus.classhandler.common.line.callers.array;

import org.cyrus.classhandler.common.classtype.ArrayClass;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.util.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefinedArrayConstructorCall<C extends CommonClass> extends ArrayConstructorCall<C> {

    protected List<Line<C>> parameters = new ArrayList<>();

    public DefinedArrayConstructorCall(C attached, ArrayClass<? extends ArrayClass, ? extends CommonClass> call) {
        super(attached, call);
    }

    public List<Line<C>> getParameters(){
        return this.parameters;
    }

    @Override
    public boolean isWrittenCorrectly() {
        for(Line<C> line : this.parameters){
            if (!line.getReturn().isPresent()){
                return false;
            }
            CommonClass<? extends CommonClass> returnValue = line.getReturn().get();
            if(!returnValue.isInstanceOf(this.attached)){
                return false;
            }
        }
        return true;
    }

    @Override
    public Optional<String> getDescriptionOfError() {
        for(Line<C> line : this.parameters){
            if (!line.getReturn().isPresent()){
                return Optional.of("Member does not return a value");
            }
            CommonClass<? extends CommonClass> returnValue = line.getReturn().get();
            if(!returnValue.isInstanceOf(this.attached)){
                return Optional.of("Member does not return " + this.attached.getName());
            }
        }
        return Optional.empty();
    }

    @Override
    public List<CommonClass<? extends CommonClass>> getImports() {
        List<CommonClass<? extends CommonClass>> list = new ArrayList<>();
        this.parameters.stream().forEach(l -> list.addAll(l.getImports()));
        return list;
    }

    @Override
    public String getAsJavaLine() {
        return "new " + this.attached.getName() + "{" + ArrayUtils.toString(",", t -> t.getAsJavaLine(), this.parameters) + "}";
    }
}
