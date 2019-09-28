package org.cyrus.classhandler.common.line.callers.variable;

import org.cyrus.classhandler.common.line.variable.Field;

public class FieldCaller extends VariableCaller {

    public FieldCaller(Field variable) {
        super(variable);
    }

    @Override
    public Field getCallable(){
        return (Field)super.getCallable();
    }

    @Override
    public String getAsJavaLine(){
        return getCallable().getVisibility().getJavaLine() + (getCallable().isStatic() ? "static ": "") + super.getAsJavaLine();
    }
}
