package org.cyrus.classhandler.common.line.operators;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Caller;
import org.cyrus.classhandler.common.line.Line;

import java.util.ArrayList;
import java.util.List;

public interface Operator extends Line {

    List<Caller> getAttached();
    int getMaxAttachments();

    @Override
    default List<CommonClass<? extends CommonClass>> getImports(){
        return new ArrayList<>();
    }

    @Override
    default CommonClass getAttachedClass() {
        return getAttached().get(0).getAttachedClass();
    }

}
