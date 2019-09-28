package org.cyrus.classhandler.common.line.callers.classcall;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Callable;
import org.cyrus.classhandler.common.line.Caller;
import org.cyrus.classhandler.java.classtype.primitive.ZeroPackageJavaClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NewCharacterCall implements Caller {

    protected char value;

    public NewCharacterCall(char value){
        this.value = value;
    }

    public char getCharacter(){
        return this.value;
    }

    @Override
    public Callable getCallable() {
        return getAttachedClass();
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
    public CommonClass getAttachedClass() {
        return ZeroPackageJavaClass.CHAR;
    }

    @Override
    public List<CommonClass<? extends CommonClass>> getImports() {
        return new ArrayList<>();
    }

    @Override
    public String getAsJavaLine() {
        return "'" + this.value + "'";
    }
}
