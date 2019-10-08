package org.cyrus.classhandler.custom.function;

import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.custom.classtype.AbstractCommonCustomClass;

public abstract class CustomFunctionBuilder <C extends AbstractCommonCustomClass> {

    protected AbstractCommonCustomClass.AppliedGenerics generics;
    protected C attached;
    protected Visibility visibility = Visibility.PUBLIC;

    public abstract CustomFunction<C> build();

    public CustomFunctionBuilder<C> setGenerics(AbstractCommonCustomClass.AppliedGenerics class1){
        this.generics = class1;
        return this;
    }

    public CustomFunctionBuilder<C> setAttachedClass(C class1){
        this.attached = class1;
        return this;
    }

    public CustomFunctionBuilder<C> setVisibility(Visibility vis){
        this.visibility = vis;
        return this;
    }

}
