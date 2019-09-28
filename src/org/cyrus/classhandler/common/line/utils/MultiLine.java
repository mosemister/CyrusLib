package org.cyrus.classhandler.common.line.utils;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.util.ArrayUtils;

import java.util.*;

public class MultiLine implements Line {

    protected List<Line> lines = new ArrayList<>();

    public MultiLine(Line... lines){
        this(Arrays.asList(lines));
    }

    public MultiLine(Collection<Line> lines){
        if(lines.isEmpty()){
            System.err.println("new MultiLine -> Lines must be included on init");
        }
        this.lines.addAll(lines);
    }

    public List<Line> getLines(){
        return this.lines;
    }

    @Override
    public boolean isWrittenCorrectly() {
        return this.lines.stream().anyMatch(l -> l.isWrittenCorrectly());
    }

    @Override
    public Optional<String> getDescriptionOfError() {
        Optional<Line> opLine = this.lines.stream().filter(l -> l.isWrittenCorrectly()).findFirst();
        if(!opLine.isPresent()){
            return Optional.empty();
        }
        return opLine.get().getDescriptionOfError();
    }

    @Override
    public CommonClass getAttachedClass() {
        return this.lines.get(0).getAttachedClass();
    }

    @Override
    public List<CommonClass<? extends CommonClass>> getImports() {
        List<CommonClass<? extends CommonClass>> list = new ArrayList<>();
        this.lines.stream().forEach(l -> list.addAll(l.getImports()));
        return list;
    }

    @Override
    public String getAsJavaLine() {
        return ArrayUtils.toString(".", f -> f.getAsJavaLine(), this.lines);
    }

    @Override
    public Optional<CommonClass<? extends CommonClass>> getReturn() {
        return this.lines.get(this.lines.size() - 1).getReturn();
    }
}
