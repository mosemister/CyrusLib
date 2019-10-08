package org.cyrus.classhandler.common.line.operators.math;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.Writable;
import org.cyrus.classhandler.java.classtype.primitive.ZeroPackageJavaClass;

import java.util.Arrays;
import java.util.Optional;

public abstract class AbstractMathOperator<C extends CommonClass> implements MathOperator<C>, Writable {

    protected Line<C> left;
    protected Line<C> right;

    public AbstractMathOperator(Line<C> left, Line<C> right){
        this.left = left;
        this.right = right;
    }

    @Override
    public Line<C> getLeft() {
        return this.left;
    }

    @Override
    public Line<C> getRight() {
        return this.right;
    }

    @Override
    public boolean isWrittenCorrectly() {
        for(Line<C> line : Arrays.asList(this.left, this.right)){
            Optional<CommonClass<? extends CommonClass>> opReturn = line.getReturn();
            if(!opReturn.isPresent()){
                return false;
            }
            if(opReturn.get() instanceof ZeroPackageJavaClass.JavaPrimitiveClass){
                continue;
            }else if(opReturn.get().isMatch(ZeroPackageJavaClass.STRING)){
                continue;
            }
            return false;
        }
        return true;
    }

    @Override
    public Optional<String> getDescriptionOfError() {
        for(Line<C> line : Arrays.asList(this.left, this.right)){
            Optional<CommonClass<? extends CommonClass>> opReturn = line.getReturn();
            if(!opReturn.isPresent()){
                return Optional.of("Value does not return a Number or String");
            }
            if(opReturn.get() instanceof ZeroPackageJavaClass.JavaPrimitiveClass){
                continue;
            }else if(opReturn.get().isMatch(ZeroPackageJavaClass.STRING)){
                continue;
            }
            return Optional.of("Value does not return a Number or String");
        }
        return Optional.empty();
    }

}
