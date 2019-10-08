package org.cyrus.classhandler.custom.variable;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.variable.Variable;
import org.cyrus.classhandler.custom.classtype.CommonCustomClass;

import java.util.Optional;

public class CustomLocalVariable<C extends CommonCustomClass> extends CustomVariable<C> implements Variable.Attachable<C> {

    protected Line<C> line;
    protected CommonClass<? extends CommonClass> ret;

    public CustomLocalVariable(C attached, String name, CommonClass<? extends CommonClass> ret) {
        super(attached, name);
        this.ret = ret;
    }

    public CustomLocalVariable(String name, Line<C> line) {
        super((C)line.getAttachedClass(), name);
        this.line = line;
    }

    @Override
    public Optional<Line<C>> getAttachedLine() {
        return Optional.ofNullable(this.line);
    }

    @Override
    public Optional<CommonClass<? extends CommonClass>> getReturn() {
        if(this.line == null){
            return Optional.of(this.ret);
        }
        return this.line.getReturn();
    }
}
