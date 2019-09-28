package org.cyrus.classhandler.common.line;

import org.cyrus.classhandler.common.JavaParsed;
import org.cyrus.classhandler.common.classtype.CommonClass;

import java.util.List;
import java.util.Optional;

public interface Line extends JavaParsed, Returnable {

    boolean isWrittenCorrectly();
    Optional<String> getDescriptionOfError();
    CommonClass getAttachedClass();
    List<CommonClass<? extends CommonClass>> getImports();
}
