package org.cyrus.classhandler.common.line.statement.ifstatement;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Line;

public class IfElseStatement<X extends CommonClass> extends IfStatement<X> {

    public IfElseStatement(Line<X> line) {
        super(line);
    }

    @Override
    public String getAsJavaLine() {
        return "else " + super.getAsJavaLine();
    }
}
