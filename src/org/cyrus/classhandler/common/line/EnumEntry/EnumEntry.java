package org.cyrus.classhandler.common.line.EnumEntry;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.classtype.EnumClass;
import org.cyrus.classhandler.common.line.Callable;

import java.util.Optional;

public interface EnumEntry extends Callable {

    EnumClass<? extends EnumClass> getAttachedClass();

    @Override
    default Optional<CommonClass<? extends CommonClass>> getReturn() {
        return Optional.of(getAttachedClass());
    }

}
