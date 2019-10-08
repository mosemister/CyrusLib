package org.cyrus.classhandler.custom.classtype;

import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.classtype.ArrayClass;
import org.cyrus.classhandler.common.classtype.GenericList;
import org.cyrus.classhandler.common.line.variable.Field;
import org.cyrus.classhandler.custom.variable.field.CustomField;
import org.cyrus.classhandler.java.classtype.primitive.ZeroPackageJavaClass;

import java.util.ArrayList;
import java.util.List;

public class ArrayCustomClass<A extends AbstractCommonCustomClass, C extends ArrayCustomClass> extends ArrayClass<A, C> implements CommonCustomClass<C>{

    public ArrayCustomClass(A clazz) {
        super(clazz);
    }

    @Override
    public List<? extends Field> getFields() {
        List<CustomField<ArrayCustomClass>> fields = new ArrayList<>();
        CustomField<ArrayCustomClass> length = new CustomField(this, "length", ZeroPackageJavaClass.INTEGER);
        fields.add(length);
        return fields;
    }

    @Deprecated
    @Override
    public AppliedGenerics toAppliedGenerics(String name) {
        return this.getNoneArrayClass().toAppliedGenerics(name);
    }

    @Deprecated
    @Override
    public boolean hasGenerics() {
        return false;
    }

    @Deprecated
    @Override
    public GenericList getGenerics() {
        return new GenericList();
    }

    @Deprecated
    @Override
    public Writable setStatic(boolean tatic) {
        return this;
    }

    @Deprecated
    @Override
    public Writable setPackage(String... ackage) {
        return this;
    }

    @Deprecated
    @Override
    public Writable setName(String name) {
        return this;
    }

    @Deprecated
    @Override
    public Writable setVisibility(Visibility visibility) {
        return this;
    }
}
