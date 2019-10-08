package org.cyrus.classhandler.custom.variable.field;

import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.variable.Field;
import org.cyrus.classhandler.custom.classtype.AbstractCommonCustomClass;
import org.cyrus.classhandler.custom.classtype.CommonCustomClass;
import org.cyrus.classhandler.custom.variable.CustomVariable;

import java.util.Optional;

public class CustomField<C extends CommonCustomClass> extends CustomVariable<C> implements Field.Writable<C>  {

    protected Visibility visibility;
    protected boolean isStatic;
    protected CommonClass<? extends CommonClass> returnValue;

    public CustomField(C attached, String name, CommonClass<? extends CommonClass> clazz) {
        super(attached, name);
        this.returnValue = clazz;
    }

    @Override
    public Visibility getVisibility() {
        return this.visibility;
    }

    @Override
    public boolean isStatic() {
        return this.isStatic;
    }

    @Override
    public Field.Writable<C> setVisibility(Visibility vis) {
        this.visibility = vis;
        return this;
    }

    @Override
    public Field.Writable<C> setStatic(boolean tatic) {
        this.isStatic = tatic;
        return this;
    }

    @Override
    public Optional<CommonClass<? extends CommonClass>> getReturn() {
        return Optional.of(this.returnValue);
    }
}
