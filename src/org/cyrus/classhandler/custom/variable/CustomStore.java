package org.cyrus.classhandler.custom.variable;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.variable.Store;

import java.util.Optional;

public abstract class CustomStore implements Store.Writable {

    protected String name;
    protected boolean final1;
    protected CommonClass attached;

    public CustomStore(CommonClass<? extends CommonClass> attached, String name){
        this.name = name;
        this.attached = attached;
    }

    @Override
    public CustomStore setName(String name){
        this.name = name;
        return this;
    }

    @Override
    public CustomStore setFinal(boolean final1){
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
    public CommonClass getAttachedClass() {
        return this.attached;
    }
}
