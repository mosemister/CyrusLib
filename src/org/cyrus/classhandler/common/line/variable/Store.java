package org.cyrus.classhandler.common.line.variable;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Callable;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.callers.variable.VariableCaller;

import java.util.Optional;

public interface Store extends Callable {

    String getName();
    boolean isFinal();
    CommonClass getAttachedClass();

    @Override
    default VariableCaller getCaller(){
        return new VariableCaller(this);
    }

    interface Attachable extends Store {

        Optional<Line> getAttachedLine();

        @Override
        default Optional<CommonClass<? extends CommonClass>> getReturn() {
            Optional<Line> opLine = getAttachedLine();
            if (opLine.isPresent()) {
                return Optional.empty();
            }
            return opLine.get().getReturn();
        }
    }

    interface Writable extends Store {

        Writable setName(String name);
        Writable setFinal(boolean inal);

    }
}
