package org.cyrus.classhandler.common.line.operators;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.Writable;

import java.util.List;
import java.util.Optional;

public class ReturnOperator<X extends CommonClass> implements Line<X>, Writable {

    protected Line<X> attached;

    public ReturnOperator(Line<X> line){
        this.attached = line;
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
        return this.attached.getAttachedClass();
    }

    @Override
    public List<CommonClass<? extends CommonClass>> getImports() {
        return this.attached.getImports();
    }

    @Override
    public String getAsJavaLine() {
        return "return " + this.attached.getAsJavaLine();
    }

    @Override
    public Optional<CommonClass<? extends CommonClass>> getReturn() {
        return this.attached.getReturn();
    }
}
