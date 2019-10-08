package org.cyrus.classhandler.common.line.operators;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.Writable;

import java.util.List;
import java.util.Optional;

public class BraceOperator<X extends CommonClass> implements Line<X>, Writable {

    protected Line<X> inside;

    public BraceOperator(Line<X> inside){
        this.inside = inside;
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
        return this.inside.getAttachedClass();
    }

    @Override
    public List<CommonClass<? extends CommonClass>> getImports() {
        return this.inside.getImports();
    }

    @Override
    public String getAsJavaLine() {
        return "(" + this.inside.getAsJavaLine() + ")";
    }

    @Override
    public Optional<CommonClass<? extends CommonClass>> getReturn() {
        return this.inside.getReturn();
    }
}
