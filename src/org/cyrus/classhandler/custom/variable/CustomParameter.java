package org.cyrus.classhandler.custom.variable;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.function.Function;
import org.cyrus.classhandler.common.line.variable.Parameter;
import org.cyrus.classhandler.custom.classtype.AbstractCommonCustomClass;
import org.cyrus.classhandler.custom.classtype.CommonCustomClass;

import java.util.Optional;

/**
 * The implementation of Parameters for custom functions
 * @param <C> The attached class
 */
public class CustomParameter<C extends CommonCustomClass> extends CustomVariable<C> implements Parameter.Writable<C> {

    protected Function<C> attached;
    protected CommonClass<? extends CommonClass> return1;

    public CustomParameter(Function<C> attached, CommonClass<? extends CommonClass> return1, String name) {
        super(attached.getAttachedClass(), name);
        this.attached = attached;
        this.return1 = return1;
    }

    @Override
    public C getAttachedClass(){
        return Parameter.Writable.super.getAttachedClass();
    }

    @Override
    public Function<C> getAttachedFunction() {
        return this.attached;
    }

    @Override
    public Optional<CommonClass<? extends CommonClass>> getReturn() {
        return Optional.of(this.return1);
    }

    @Override
    public Parameter.Writable<C> setReturn(CommonClass<? extends CommonClass> class1) {
        this.return1 = class1;
        return this;
    }

}
