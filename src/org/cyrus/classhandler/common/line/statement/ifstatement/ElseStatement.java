package org.cyrus.classhandler.common.line.statement.ifstatement;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.statement.Statement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ElseStatement<X extends CommonClass> implements Statement.InLine<X> {

    protected X attached;
    protected List<Line<? extends CommonClass>> lines = new ArrayList<>();

    public ElseStatement(X clazz){
        this.attached = clazz;
    }

    @Override
    public List<CommonClass<? extends CommonClass>> getStatementRequirements() {
        return new ArrayList<>();
    }

    @Override
    public List<Line<X>> getStatementParameters() {
        return new ArrayList<>();
    }

    @Override
    public boolean isWrittenCorrectly() {
        return true;
    }

    @Override
    public Optional<String> getDescriptionOfError() {
        return Optional.empty();
    }

    @Override
    public CommonClass<X> getAttachedClass() {
        return this.attached;
    }

    @Override
    public List<CommonClass<? extends CommonClass>> getImports() {
        return new ArrayList<>();
    }

    @Override
    public String getAsJavaLine() {
        return "else";
    }

    @Override
    public List<Line<? extends CommonClass>> getLines() {
        return this.lines;
    }
}
