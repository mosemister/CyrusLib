package org.cyrus.classhandler.common.line.operators.math;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.java.classtype.primitive.ZeroPackageJavaClass;

import java.util.Optional;

public class LessThanOperator<X extends CommonClass> extends AbstractMathOperator<X> {

    public LessThanOperator(Line<X> left, Line<X> right) {
        super(left, right);
    }

    @Override
    public String getSign() {
        return "<";
    }

    @Override
    public Optional<CommonClass<? extends CommonClass>> getReturn(){
        return Optional.of(ZeroPackageJavaClass.BOOLEAN);
    }
}
