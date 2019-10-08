package org.cyrus.classhandler.java.classtype;

import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.classtype.ArrayClass;
import org.cyrus.classhandler.common.classtype.GenericList;
import org.cyrus.classhandler.common.line.variable.Field;
import org.cyrus.classhandler.java.classtype.primitive.ZeroPackageJavaClass;
import org.cyrus.classhandler.java.variable.WField;

import java.util.ArrayList;
import java.util.List;

public class ArrayJavaClass<A extends AbstractCommonJavaClass, C extends ArrayJavaClass> extends ArrayClass<A, C> implements CommonJavaClass<C> {

    public ArrayJavaClass(A clazz) {
        super(clazz);
    }

    @Override
    public List<? extends Field> getFields() {
        List<WField> list = new ArrayList();
        list.add(new WField(Visibility.PUBLIC, true, true, ZeroPackageJavaClass.INTEGER, "length", this));
        return list;
    }

    @Deprecated
    @Override
    public AppliedGenerics toAppliedGenerics(String name) {
        return this.getNoneArrayClass().toAppliedGenerics(name);
    }

    @Override
    public boolean hasGenerics() {
        return false;
    }

    @Override
    public GenericList getGenerics() {
        return new GenericList();
    }

    @Override
    public boolean equalsExact(Class class1) {
        return getNoneArrayClass().equalsExact(class1);
    }
}
