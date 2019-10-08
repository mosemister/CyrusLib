package org.cyrus.classhandler.java.classtype;

import org.cyrus.classhandler.common.classtype.CommonClass;

public interface CommonJavaClass<C extends CommonJavaClass> extends CommonClass<C> {

    boolean equalsExact(Class<? extends CommonClass> class1);
}
