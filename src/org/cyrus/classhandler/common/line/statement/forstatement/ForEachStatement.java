package org.cyrus.classhandler.common.line.statement.forstatement;

import org.cyrus.classhandler.common.classtype.ArrayClass;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.statement.Statement;
import org.cyrus.classhandler.common.line.variable.Variable;
import org.cyrus.classhandler.java.classtype.ArrayJavaClass;
import org.cyrus.classhandler.java.classtype.AbstractCommonJavaClass;
import org.cyrus.util.ArrayUtils;

import java.util.*;

public class ForEachStatement<X extends CommonClass> implements Statement.InLine<X>, Variable.Writable<X>{

    protected List<Line<? extends CommonClass>> lines = new ArrayList<>();
    protected Line<X> value;
    protected CommonClass<? extends CommonClass> ret;
    protected String name;
    protected boolean isFinal;

    public ForEachStatement(String name, Line<X> value, CommonClass<? extends CommonClass> ret){
        this.value = value;
        this.ret = ret;
        this.name = name;
    }

    @Override
    public List<Line<? extends CommonClass>> getLines() {
        return this.lines;
    }

    @Override
    public List<CommonClass<? extends CommonClass>> getStatementRequirements() {
        return Arrays.asList(AbstractCommonJavaClass.of(Iterable.class), new ArrayJavaClass<>(AbstractCommonJavaClass.of(Object.class)));
    }

    @Override
    public List<Line<X>> getStatementParameters() {
        return Collections.singletonList(this.value);
    }

    @Override
    public boolean isWrittenCorrectly() {
        Optional<CommonClass<? extends CommonClass>> opRet = this.value.getReturn();
        if(!opRet.isPresent()){
            return false;
        }
        if(opRet.get() instanceof ArrayClass){
            return true;
        }
        if(opRet.get().isInstanceOf(AbstractCommonJavaClass.of(Iterable.class))){
            return true;
        }
        return false;
    }

    @Override
    public Optional<String> getDescriptionOfError() {
        Optional<CommonClass<? extends CommonClass>> opRet = this.value.getReturn();
        if(!opRet.isPresent()){
            return Optional.of("Line must return an array or iterable");
        }
        if(opRet.get() instanceof ArrayClass){
            return Optional.empty();
        }
        if(opRet.get().isInstanceOf(AbstractCommonJavaClass.of(Iterable.class))){
            return Optional.empty();
        }
        return Optional.of("Line must return an array or iterable");
    }

    @Override
    public CommonClass<X> getAttachedClass() {
        return this.value.getAttachedClass();
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
    public List<CommonClass<? extends CommonClass>> getImports() {
        List<CommonClass<? extends CommonClass>> list = new ArrayList<>();
        list.add(this.ret);
        list.addAll(this.value.getImports());
        this.lines.stream().forEach(l -> list.addAll(l.getImports()));
        return list;
    }

    @Override
    public String getAsJavaLine() {
        return "for (" + this.ret.getName() + " " + this.name + " : " + this.value.getAsJavaLine() + ")";
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
}
