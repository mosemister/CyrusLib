package org.cyrus.classhandler.custom.reader;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.classtype.StandardClass;
import org.cyrus.classhandler.common.function.constructor.Constructor;
import org.cyrus.classhandler.common.function.method.Method;
import org.cyrus.classhandler.common.line.variable.Field;
import org.cyrus.classhandler.custom.classtype.CommonCustomClass;
import org.cyrus.classhandler.custom.classtype.CustomClassBuilder;

import java.util.Optional;
import java.util.Set;

public interface ClassReader<C extends CommonCustomClass> {

    String[] readPackage();
    Set<CommonClass> readImports();
    C readClassInit();
    Optional<StandardClass> readExtends();
    Set<CommonClass> readImplements();
    Set<Field> readFields();
    Set<Constructor<C>> readConstructors(CommonCustomClass clazz);
    Set<Method<C>> readMethods(CommonCustomClass clazz);
    String getClassType();
    ClassReader<C> newInstance(String... lines);
}
