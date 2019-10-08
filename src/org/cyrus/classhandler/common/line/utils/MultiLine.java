package org.cyrus.classhandler.common.line.utils;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.Writable;
import org.cyrus.util.ArrayUtils;

import java.util.*;

/**
 * Used for joining multiple parts of a line together
 * such as multiple method calls on a single line
 */
public class MultiLine<X extends CommonClass> implements Line<X>, Writable {

    protected List<Line<X>> lines = new ArrayList<>();

    public MultiLine(Line<X>... lines){
        this(Arrays.asList(lines));
    }

    public MultiLine(Collection<Line<X>> lines){
        if(lines.isEmpty()){
            throw new IndexOutOfBoundsException("new MultiLine -> Lines must be included on init");
        }
        this.lines.addAll(lines);
    }

    /**
     * Gets the sections within this line
     * @return All parts to the line
     */
    public List<Line<X>> getLines(){
        return this.lines;
    }

    /**
     * checks all parts are written correctly
     * @return
     */
    @Override
    public Optional<String> getDescriptionOfError() {
        Optional<Line<X>> opLine = this.lines.stream().filter(l -> l instanceof Writable).filter(l -> ((Writable)l).isWrittenCorrectly()).findFirst();
        if(!opLine.isPresent()){
            return Optional.empty();
        }
        return ((Writable)opLine.get()).getDescriptionOfError();
    }

    /**
     * gets the attached class
     * @return the attached class
     */
    @Override
    public CommonClass<X> getAttachedClass() {
        return this.lines.get(0).getAttachedClass();
    }

    /**
     * gets all the imports for the line
     * @return all imports
     */
    @Override
    public List<CommonClass<? extends CommonClass>> getImports() {
        List<CommonClass<? extends CommonClass>> list = new ArrayList<>();
        this.lines.stream().forEach(l -> list.addAll(l.getImports()));
        return list;
    }

    /**
     * Gets the line as java
     * @return The java line
     */
    @Override
    public String getAsJavaLine() {
        return ArrayUtils.toString(".", f -> f.getAsJavaLine(), this.lines);
    }

    /**
     * gets the last return in the line
     * @return the returning value
     */
    @Override
    public Optional<CommonClass<? extends CommonClass>> getReturn() {
        return this.lines.get(this.lines.size() - 1).getReturn();
    }
}
