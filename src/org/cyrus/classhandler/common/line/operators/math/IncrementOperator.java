package org.cyrus.classhandler.common.line.operators.math;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.Writable;
import org.cyrus.classhandler.common.line.callers.variable.VariableCaller;
import org.cyrus.classhandler.common.line.variable.Variable;
import org.cyrus.classhandler.java.classtype.AbstractCommonJavaClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IncrementOperator<X extends CommonClass> implements Line<X>, Writable {

    protected VariableCaller<? extends Variable, X> line;

    public IncrementOperator(VariableCaller<? extends Variable, X> caller){
        this.line = caller;
    }

    @Override
    public boolean isWrittenCorrectly() {
        if(!this.line.getReturn().isPresent()){
            return false;
        }
        if(this.line.getReturn().get().isInstanceOf(AbstractCommonJavaClass.of(Number.class))){
            return true;
        }
        return false;
    }

    @Override
    public Optional<String> getDescriptionOfError() {
        if(!this.line.getReturn().isPresent()){
            return Optional.of("Variable must be a number");
        }
        if(this.line.getReturn().get().isInstanceOf(AbstractCommonJavaClass.of(Number.class))){
            return Optional.empty();
        }
        return Optional.of("Variable must be a number");
    }

    @Override
    public CommonClass<X> getAttachedClass() {
        return this.line.getAttachedClass();
    }

    @Override
    public List<CommonClass<? extends CommonClass>> getImports() {
        return new ArrayList<>();
    }

    @Override
    public String getAsJavaLine() {
        return this.line.getAsJavaLine() + "++";
    }

    @Override
    public Optional<CommonClass<? extends CommonClass>> getReturn() {
        return this.line.getReturn();
    }
}
