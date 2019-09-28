package org.cyrus.classhandler.custom.function.method.cmethod;

import org.cyrus.classhandler.custom.classtype.StandardCustomClass;
import org.cyrus.classhandler.custom.function.method.CustomMethod;

public class AbstractCustomMethod <C extends StandardCustomClass> extends CustomMethod<C> {

    public AbstractCustomMethod(C attached, String name) {
        super(attached, name);
    }
}
