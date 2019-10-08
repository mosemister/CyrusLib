package org.cyrus.classhandler.common.line.operators.math;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Line;

public class MinusOperator<C extends CommonClass> extends AbstractMathOperator<C> {

    public MinusOperator(Line<C> left, Line<C> right) {
        super(left, right);
    }

    @Override
    public String getSign() {
        return "-";
    }
}
