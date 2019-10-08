package org.cyrus.exception;

import org.cyrus.classhandler.common.line.statement.Statement;

import java.io.IOException;
import java.util.Map;

public class StatementAppendException extends IOException {

    protected Map<Statement, String> map;

    public StatementAppendException(Map<Statement, String> map){
        super(map.toString());
        this.map = map;
    }

    public Map<Statement, String> getStatements(){
        return this.map;
    }
}
