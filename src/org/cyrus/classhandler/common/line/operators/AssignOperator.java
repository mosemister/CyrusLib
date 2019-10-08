package org.cyrus.classhandler.common.line.operators;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.Writable;
import org.cyrus.classhandler.common.line.variable.Field;
import org.cyrus.classhandler.common.line.variable.Variable;

import java.util.List;
import java.util.Optional;

public class AssignOperator<X extends CommonClass> implements Line<X>, Writable {

    protected Variable<? extends CommonClass> variable;
    protected Line<X> attached;

    public AssignOperator(Variable<? extends CommonClass> variable, Line<X> attached){
        this.variable = variable;
        this.attached = attached;
    }

    @Override
    public boolean isWrittenCorrectly() {
        Optional<CommonClass<? extends CommonClass>> opReturn = this.attached.getReturn();
        if(opReturn.isPresent()){
            if(opReturn.get().isInstanceOf(this.variable.getReturn().get())){
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<String> getDescriptionOfError() {
        Optional<CommonClass<? extends CommonClass>> opReturn = this.attached.getReturn();
        if(opReturn.isPresent()){
            if(opReturn.get().isInstanceOf(this.variable.getReturn().get())){
                return Optional.empty();
            }
        }
        return Optional.of("Line does not return a " + this.variable.getReturn().get().getName());
    }

    @Override
    public CommonClass<X> getAttachedClass() {
        return this.attached.getAttachedClass();
    }

    @Override
    public List<CommonClass<? extends CommonClass>> getImports() {
        return this.attached.getImports();
    }

    @Override
    public String getAsJavaLine() {
        String nameField = this.variable.getName();
        if(this.variable instanceof Field){
            Field field = (Field)this.variable;
            if (field.getAttachedClass().isMatch(this.attached.getAttachedClass())){
                if (!field.isStatic()) {
                    nameField = "this." + nameField;
                }
            }
        }
        return nameField + " = " + this.attached.getAsJavaLine();
    }

    @Override
    public Optional<CommonClass<? extends CommonClass>> getReturn() {
        return this.attached.getReturn();
    }
}
