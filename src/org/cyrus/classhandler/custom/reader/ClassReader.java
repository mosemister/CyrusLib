package org.cyrus.classhandler.custom.reader;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.classtype.StandardClass;
import org.cyrus.classhandler.common.function.constructor.Constructor;
import org.cyrus.classhandler.common.function.method.Method;
import org.cyrus.classhandler.common.line.variable.Field;
import org.cyrus.classhandler.custom.classtype.AbstractCommonCustomClass;

import java.util.Optional;
import java.util.Set;

public interface ClassReader<C extends AbstractCommonCustomClass> {

    String[] readPackage();
    Set<CommonClass> readImports();
    C readClassInit();
    Optional<StandardClass> readExtends();
    Set<CommonClass> readImplements();
    Set<Field> readFields();
    Set<Constructor<C>> readConstructors(AbstractCommonCustomClass clazz);
    Set<Method<C>> readMethods(AbstractCommonCustomClass clazz);
    String getClassType();
    ClassReader<C> newInstance(String... lines);
}
