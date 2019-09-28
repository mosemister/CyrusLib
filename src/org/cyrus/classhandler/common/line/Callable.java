package org.cyrus.classhandler.common.line;

import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.variable.Parameter;

import java.util.List;

public interface Callable extends Returnable {

    String getName();
    Caller getCaller();

    interface StandardCallable extends Callable {

        Visibility getVisibility();

        List<Parameter<? extends CommonClass>> getParameters();
    }

}
