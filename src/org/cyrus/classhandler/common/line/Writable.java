package org.cyrus.classhandler.common.line;

import java.util.Optional;

public interface Writable {

    Optional<String> getDescriptionOfError();

    default boolean isWrittenCorrectly(){
        return getDescriptionOfError().isPresent();
    }
}
