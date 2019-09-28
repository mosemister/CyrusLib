package org.cyrus.classhandler.custom.function.method.imethod;

import org.cyrus.classhandler.common.classtype.InterfaceClass;
import org.cyrus.classhandler.common.function.method.imethod.InterfaceMethod;
import org.cyrus.classhandler.custom.function.method.CustomMethod;

public class InterfaceCustomMethod extends CustomMethod<InterfaceClass> implements InterfaceMethod<InterfaceClass> {

    public InterfaceCustomMethod(InterfaceClass attached, String name) {
        super(attached, name);
    }
}
