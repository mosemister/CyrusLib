package org.cyrus.classhandler.common.line.statement.forstatement;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.callers.variable.VariableCaller;
import org.cyrus.classhandler.common.line.statement.Statement;
import org.cyrus.classhandler.common.line.variable.Variable;
import org.cyrus.classhandler.java.classtype.primitive.ZeroPackageJavaClass;
import org.cyrus.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ForStatement<X extends CommonClass> implements Statement.InLine<X>, Variable.Writable<X> , Variable.Attachable<X> {

    protected List<Line<? extends CommonClass>> lines = new ArrayList<>();
    protected Line<X> value;
    protected Line<X> check;
    protected Line<X> init;
    protected String name;
    protected boolean isFinal;

    public ForStatement(String name, Line<X> init, Function<VariableCaller, Line<X>> check, Function<VariableCaller, Line<X>> value){
        this.value = value.apply(createCaller(init.getAttachedClass()));
        this.name = name;
        this.check = check.apply(createCaller(init.getAttachedClass()));
        this.init = init;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String[] getSplitName() {
        return ArrayUtils.splitBy(getName(), 0, true, c -> Character.isUpperCase(c));
    }

    @Override
    public boolean isFinal() {
        return this.isFinal;
    }

    @Override
    public Writable<X> setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Writable<X> setFinal(boolean inal) {
        this.isFinal = inal;
        return this;
    }

    @Override
    public CommonClass<X> getAttachedClass() {
        return this.value.getAttachedClass();
    }

    @Override
    public List<CommonClass<? extends CommonClass>> getStatementRequirements() {
        return Arrays.asList(ZeroPackageJavaClass.BOOLEAN);
    }

    @Override
    public List<Line<X>> getStatementParameters() {
        return Arrays.asList(this, this.init, this.check, this.value);
    }

    @Override
    public Optional<Line<X>> getAttachedLine() {
        return Optional.of(this.init);
    }

    @Override
    public List<Line<? extends CommonClass>> getLines() {
        return this.lines;
    }

    @Override
    public boolean isWrittenCorrectly(){
        if(!this.init.getReturn().isPresent()){
            return false;
        }
        if(!this.check.getReturn().isPresent()){
            return false;
        }
        if(!this.check.getReturn().get().isMatch(ZeroPackageJavaClass.BOOLEAN)){
            return false;
        }
        return true;
    }

    @Override
    public Optional<String> getDescriptionOfError(){
        if(!this.init.getReturn().isPresent()){
            return Optional.of("Variable must have value");
        }
        if(!this.check.getReturn().isPresent()){
            return Optional.of("Variable must have value");
        }
        if(!this.check.getReturn().get().isMatch(ZeroPackageJavaClass.BOOLEAN)){
            return Optional.of("Compare must return boolean");
        }
        return Optional.empty();
    }

    @Override
    public String getAsJavaLine(){
        return "for(" + this.init.getReturn().get().getName() + " " + this.name + " = " + this.init.getAsJavaLine() + ": " + this.check.getAsJavaLine() + ": " + this.value.getAsJavaLine() + ")";
    }

    @Override
    public Optional<CommonClass<? extends CommonClass>> getReturn(){
        return Variable.Attachable.super.getReturn();
    }
}
