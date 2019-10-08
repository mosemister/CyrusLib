package org.cyrus.classhandler.custom.function.constructor;

import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.classtype.EnumClass;
import org.cyrus.classhandler.common.classtype.StandardClass;
import org.cyrus.classhandler.common.function.Function;
import org.cyrus.classhandler.common.function.constructor.Constructor;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.variable.Parameter;
import org.cyrus.classhandler.custom.classtype.AbstractCommonCustomClass;
import org.cyrus.classhandler.custom.function.CustomFunction;
import org.cyrus.classhandler.custom.function.CustomFunctionBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomConstructor<C extends AbstractCommonCustomClass> extends CustomFunction<C> implements Constructor.Writable<C>, Function.Visible<C> {

    protected List<Line<? extends CommonClass>> line = new ArrayList<>();

    public CustomConstructor(C attached) {
        super(attached);
    }

    @Override
    public List<Line<? extends CommonClass>> getLines() {
        return this.line;
    }

    @Override
    public Visible<C> setVisibility(Visibility vis) {
        this.visibility = vis;
        return this;
    }

    @Override
    public Optional<String> getDescriptionOfError() {
        Optional<Parameter<? extends CommonClass>> opParameter = getParameters().stream().filter(p -> p.getDescriptionOfError().isPresent()).findAny();
        if(opParameter.isPresent()){
            return opParameter.get().getDescriptionOfError();
        }
        Optional<Line<? extends CommonClass>> opLine = getLines().stream().filter(l -> l instanceof org.cyrus.classhandler.common.line.Writable).filter(l -> ((org.cyrus.classhandler.common.line.Writable) l).isWrittenCorrectly()).findFirst();
        if(opLine.isPresent()){
            return ((org.cyrus.classhandler.common.line.Writable)opLine.get()).getDescriptionOfError();
        }
        return Optional.empty();
    }

    public static class Builder<C extends AbstractCommonCustomClass> extends CustomFunctionBuilder<C> {

        @Override
        public CustomConstructor<C> build() {
            if(this.attached == null){
                System.err.println("Attached class is not present using Constructor builder");
            }
            CustomConstructor<C> constructor = new CustomConstructor<>(this.attached);
            constructor.setVisibility(this.visibility);
            if(this.attached instanceof StandardClass){
                ((StandardClass) this.attached).getConstructors().add(constructor);
            } else if (this.attached instanceof EnumClass) {
                ((EnumClass) this.attached).getConstructors().add(constructor);
            }else{
                System.err.println("Attached class must accept constructors");
            }
            return constructor;
        }
    }
}
