package org.cyrus.classhandler.common.line.callers.array;

import org.cyrus.classhandler.common.classtype.ArrayClass;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.callers.classcall.NewNumberCall;
import org.cyrus.classhandler.java.classtype.primitive.ZeroPackageJavaClass;

import java.util.List;
import java.util.Optional;

public class FixedArrayConstructorCall<C extends CommonClass> extends ArrayConstructorCall<C> {

    protected Line<C> line;

    public FixedArrayConstructorCall(C attached, ArrayClass<? extends ArrayClass, ? extends CommonClass> call, int amount){
        this(call, new NewNumberCall(attached, ZeroPackageJavaClass.INTEGER, amount));
    }

    public FixedArrayConstructorCall(ArrayClass<? extends ArrayClass, ? extends CommonClass> call, Line<C> line) {
        super((C)line.getAttachedClass(), call);
        this.line = line;
    }

    @Override
    public boolean isWrittenCorrectly() {
        Optional<CommonClass<? extends CommonClass>> opReturn = this.line.getReturn();
        if(!opReturn.isPresent()){
            return false;
        }
        return opReturn.get().isMatch(ZeroPackageJavaClass.INTEGER);
    }

    @Override
    public Optional<String> getDescriptionOfError() {
        Optional<CommonClass<? extends CommonClass>> opReturn = this.line.getReturn();
        if(!opReturn.isPresent()){
            return Optional.of("Array size must be a whole number");
        }
        if (opReturn.get().isMatch(ZeroPackageJavaClass.INTEGER)){
            return Optional.empty();
        }
        return Optional.of("Array size must be a whole number");
    }

    @Override
    public List<CommonClass<? extends CommonClass>> getImports() {
        return this.line.getImports();
    }

    @Override
    public String getAsJavaLine() {
        String arrayName = this.clazz.getName();
        arrayName = arrayName.substring(0, arrayName.length() - 1) + this.line.getAsJavaLine() + "]";
        return arrayName;
    }
}
