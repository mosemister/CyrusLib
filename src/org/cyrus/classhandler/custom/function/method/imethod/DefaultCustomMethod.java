package org.cyrus.classhandler.custom.function.method.imethod;

import org.cyrus.classhandler.common.classtype.InterfaceClass;
import org.cyrus.classhandler.common.function.method.imethod.DefaultMethod;
import org.cyrus.classhandler.custom.function.method.CustomMethod;

public class DefaultCustomMethod extends CustomMethod<InterfaceClass> implements DefaultMethod<InterfaceClass> {

    public DefaultCustomMethod(InterfaceClass attached, String name) {
        super(attached, name);
    }
}
