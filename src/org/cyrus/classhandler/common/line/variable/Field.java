package org.cyrus.classhandler.common.line.variable;

import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.line.callers.variable.VariableCaller;

public interface Field extends Store {

    Visibility getVisibility();
    boolean isStatic();

    @Override
    default VariableCaller getCaller(){
        return new VariableCaller(this);
    }

    interface Writable extends Field, Store.Writable{

        Field.Writable setWritable(Visibility vis);
        Field.Writable setStatic(boolean tatic);

    }

    interface Attachable extends Field, Store.Attachable{

    }
}
