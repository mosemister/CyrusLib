package org.cyrus.classhandler.custom.variable;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.variable.Variable;
import org.cyrus.classhandler.custom.classtype.AbstractCommonCustomClass;
import org.cyrus.classhandler.custom.classtype.CommonCustomClass;

/**
 * The implementation of Variables for Custom Functions
 * @param <C> The attached class
 */
public abstract class CustomVariable<C extends CommonCustomClass> implements Variable.Writable<C> {

    protected String name;
    protected boolean final1;
    protected C attached;

    public CustomVariable(C attached, String name){
        this.name = name;
        this.attached = attached;
    }

    @Override
    public CustomVariable<C> setName(String name){
        this.name = name;
        return this;
    }

    @Override
    public CustomVariable<C> setFinal(boolean final1){
        this.final1 = final1;
        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isFinal() {
        return this.final1;
    }

    @Override
    public CommonClass<C> getAttachedClass() {
        return this.attached;
    }
}
