package org.cyrus.classhandler.common.line.EnumEntry;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.classtype.EnumClass;
import org.cyrus.classhandler.common.line.Callable;

import java.util.Optional;

/**
 * The entry within a enum
 */
public interface EnumEntry extends Callable {

    /**
     * Gets the enum class this entry is attached to
     * @return The attached enum
     */
    EnumClass<? extends EnumClass> getAttachedClass();

    /**
     * Entry will always return the enum it is attached to
     * @return an Optional of the enum attached class
     */
    @Override
    default Optional<CommonClass<? extends CommonClass>> getReturn() {
        return Optional.of(getAttachedClass());
    }

}
