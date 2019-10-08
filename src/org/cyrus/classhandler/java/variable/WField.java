package org.cyrus.classhandler.java.variable;

import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.variable.Field;
import org.cyrus.classhandler.java.classtype.CommonJavaClass;

import java.util.Optional;

public class WField<X extends CommonJavaClass> implements Field<X> {

    protected String name;
    protected boolean isStatic;
    protected boolean isFinal;
    protected Visibility visibility;
    protected X attached;
    protected CommonClass<? extends CommonClass> ret;

    public WField(Visibility vis, boolean isStatic, boolean isFinal, CommonClass<? extends CommonClass> ret, String name, X attached){
        this.visibility = vis;
        this.isFinal = isFinal;
        this.isStatic = isStatic;
        this.name = name;
        this.attached = attached;
        this.ret = ret;
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
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isFinal() {
        return this.isFinal;
    }

    @Override
    public CommonClass<X> getAttachedClass() {
        return this.attached;
    }

    @Override
    public Optional<CommonClass<? extends CommonClass>> getReturn() {
        return Optional.of(this.ret);
    }
}
