package org.cyrus.classhandler.common.line.operators.math;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.java.classtype.primitive.ZeroPackageJavaClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface MathOperator<C extends CommonClass> extends Line<C> {

    String getSign();
    Line<C> getLeft();
    Line<C> getRight();

    @Override
    default CommonClass<C> getAttachedClass() {
        return getLeft().getAttachedClass();
    }

    @Override
    default List<CommonClass<? extends CommonClass>> getImports() {
        List<CommonClass<? extends CommonClass>> list = new ArrayList<>();
        list.addAll(getLeft().getImports());
        list.addAll(getRight().getImports());
        return list;
    }

    @Override
    default String getAsJavaLine() {
        return getLeft().getAsJavaLine() + " " + getSign() + " " + getRight().getAsJavaLine();
    }

    @Override
    default Optional<CommonClass<? extends CommonClass>> getReturn() {
        if(getLeft().getReturn().get().isMatch(ZeroPackageJavaClass.STRING)){
            return Optional.of(ZeroPackageJavaClass.STRING);
        }
        if(getRight().getReturn().get().isMatch(ZeroPackageJavaClass.STRING)){
            return Optional.of(ZeroPackageJavaClass.STRING);
        }
        if(getLeft().getAsJavaLine().contains(".")){
            return Optional.of(getLeft().getReturn().get());
        }
        if(getRight().getAsJavaLine().contains(".")){
            return Optional.of(getLeft().getReturn().get());
        }
        return getLeft().getReturn();
    }
}
