package org.cyrus.classhandler.java.variable;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.function.Function;
import org.cyrus.classhandler.common.line.variable.Parameter;
import org.cyrus.classhandler.java.classtype.CommonJavaClass;

import java.lang.reflect.Modifier;
import java.util.Optional;

public class JParameter<C extends CommonJavaClass> implements Parameter<C> {

    protected java.lang.reflect.Parameter parameter;
    protected Function<C> attached;

    public JParameter(Function<C> function, java.lang.reflect.Parameter parameter){
        this.attached = function;
        this.parameter = parameter;
    }

    @Override
    public String getName() {
        return this.parameter.getName();
    }

    @Override
    public boolean isFinal() {
        return Modifier.isFinal(this.parameter.getModifiers());
    }

    @Override
    public Optional<CommonClass<? extends CommonClass>> getReturn() {
        return Optional.of(CommonJavaClass.of(this.parameter.getType()));
    }

    @Override
    public Function<C> getAttachedFunction() {
        return this.attached;
    }
}
