package org.cyrus.classhandler.custom.function.method;

import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.function.method.Method;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.variable.Parameter;
import org.cyrus.classhandler.custom.classtype.CommonCustomClass;
import org.cyrus.classhandler.custom.function.CustomFunction;
import org.cyrus.classhandler.custom.function.CustomFunctionBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CustomMethod <C extends CommonClass> extends CustomFunction<C> implements Method.Writable<C> {

    public static abstract class CustomMethodBuilder<C extends CommonCustomClass> extends CustomFunctionBuilder<C> {

        protected String name;
        protected boolean isStatic;
        protected CommonClass<? extends CommonClass> returnClass;

        public CustomMethodBuilder<C> setName(String name){
            this.name = name;
            return this;
        }

        public CustomMethodBuilder<C> setStatic(boolean static1){
            this.isStatic = static1;
            return this;
        }

        public CustomMethodBuilder<C> setReturn(CommonClass class1){
            this.returnClass = class1;
            return this;
        }

    }

    protected String name;
    protected boolean static1;
    protected CommonClass<? extends CommonClass> returns;
    protected List<Line> lines = new ArrayList<>();

    public CustomMethod(C attached, String name) {
        super(attached);
        this.name = name;
    }

    @Override
    public boolean isStatic() {
        return this.static1;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Optional<CommonClass<? extends CommonClass>> getReturn() {
        return Optional.ofNullable(this.returns);
    }

    @Override
    public CustomMethod<C> setStatic(boolean tatic) {
        this.static1 = tatic;
        return this;
    }

    @Override
    public CustomMethod<C> setReturn(CommonClass class1) {
        this.returns = class1;
        return this;
    }

    @Override
    public CustomMethod<C> setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public CustomMethod<C> setVisibility(Visibility vis) {
        this.visibility = vis;
        return this;
    }

    @Override
    public List<Line> getLines() {
        return this.lines;
    }
}
