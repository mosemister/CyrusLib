package org.cyrus.classhandler.common.line.statement.ifstatement;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.statement.Statement;
import org.cyrus.classhandler.java.classtype.primitive.ZeroPackageJavaClass;
import org.cyrus.exception.StatementAppendException;

import java.util.*;

public class IfStatement<X extends CommonClass> implements Statement.InLine.Attachable<X> {

    protected Line<X> parameter;
    protected List<Statement> attached;
    protected List<Line<? extends CommonClass>> lines = new ArrayList<>();

    public IfStatement(Line<X> line){
        this.parameter = line;
    }

    @Override
    public List<CommonClass<? extends CommonClass>> getStatementRequirements() {
        return Collections.singletonList(ZeroPackageJavaClass.BOOLEAN);
    }

    @Override
    public List<Line<X>> getStatementParameters() {
        return Collections.singletonList(this.parameter);
    }

    @Override
    public List<Statement> getAttached() {
        return this.attached;
    }

    @Override
    public Attachable<X> addStatements(Collection<Statement> statements) throws StatementAppendException {
        Map<Statement, String> map = new HashMap<>();
        for (Statement statement : statements){
            if(statement instanceof IfElseStatement){
                this.attached.add(statement);
            }else if(statement instanceof ElseStatement){
                this.attached.add(statement);
            }else{
                map.put(statement, "Statement must be 'if else' or 'else'");
            }
        }
        if(map.isEmpty()){
            return this;
        }
        throw new StatementAppendException(map);
    }

    @Override
    public Attachable<X> removeStatements(Collection<Statement> statements) throws StatementAppendException {
        Map<Statement, String> map = new HashMap<>();
        for (Statement statement : statements){
            if (!this.attached.remove(statement)){
                map.put(statement, "Statement was not attached in the first place");
            }
        }
        if(map.isEmpty()){
            return this;
        }
        throw new StatementAppendException(map);
    }

    @Override
    public boolean isWrittenCorrectly() {
        if(!this.parameter.getReturn().isPresent()){
            return false;
        }
        if(!this.parameter.getReturn().get().isInstanceOf(ZeroPackageJavaClass.BOOLEAN)){
            return false;
        }
        return true;
    }

    @Override
    public Optional<String> getDescriptionOfError() {
        if(!this.parameter.getReturn().isPresent()){
            return Optional.of("Line is not a boolean");
        }
        if(!this.parameter.getReturn().get().isInstanceOf(ZeroPackageJavaClass.BOOLEAN)){
            return Optional.of("Line is not a boolean");
        }
        return Optional.empty();
    }

    @Override
    public CommonClass<X> getAttachedClass() {
        return this.parameter.getAttachedClass();
    }

    @Override
    public List<CommonClass<? extends CommonClass>> getImports() {
        return this.parameter.getImports();
    }

    @Override
    public String getAsJavaLine() {
        return "if (" + this.parameter.getAsJavaLine() + ")";
    }

    @Override
    public List<Line<? extends CommonClass>> getLines() {
        return this.lines;
    }
}
