package org.cyrus.classhandler.common.line.callers.variable;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.variable.Field;

/**
 * Caller for fields
 */
public class FieldCaller extends VariableCaller {

    public FieldCaller(CommonClass<? extends CommonClass> clazz, Field variable) {
        super(clazz, variable);
    }

    @Override
    public Field getCallable(){
        return (Field)super.getCallable();
    }
}
