package org.cyrus.classhandler.custom.function;

import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.variable.Parameter;
import org.cyrus.classhandler.custom.classtype.CommonCustomClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class CustomFunctionBuilder <C extends CommonCustomClass> {

    protected CommonCustomClass.AppliedGenerics generics;
    protected C attached;
    protected Visibility visibility = Visibility.PUBLIC;

    public abstract CustomFunction<C> build();

    public CustomFunctionBuilder<C> setGenerics(CommonCustomClass.AppliedGenerics class1){
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
