package org.cyrus.classhandler.common.line.callers;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.classtype.EnumClass;
import org.cyrus.classhandler.common.function.constructor.Constructor;
import org.cyrus.classhandler.common.line.Callable;
import org.cyrus.classhandler.common.line.Caller;
import org.cyrus.classhandler.common.line.EnumEntry.EnumEntry;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.Writable;
import org.cyrus.classhandler.common.line.variable.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Entry call
 */
public class EnumEntryCall implements Caller, Writable {

    protected EnumEntry entry;
    protected CommonClass<? extends CommonClass> attached;

    public EnumEntryCall(CommonClass<? extends CommonClass> clazz, EnumEntry entry) {
        this.attached = clazz;
        this.entry = entry;
    }

    /**
     * Gets the callable class
     * @return the entry
     */
    @Override
    public Callable getCallable() {
        return this.entry;
    }

    /**
     * Can not write incorrectly
     * @return true
     */
    @Override
    public boolean isWrittenCorrectly() {
        return true;
    }

    /**
     * Can not write incorrectly
     * @return Optional empty
     */
    @Override
    public Optional<String> getDescriptionOfError() {
        return Optional.empty();
    }

    /**
     * Gets the attached class.
     * @return the class this call is used in
     */
    @Override
    public CommonClass<? extends CommonClass> getAttachedClass() {
        return this.attached;
    }

    /**
     * No imports for call
     * @return
     */
    @Override
    public List<CommonClass<? extends CommonClass>> getImports() {
        return new ArrayList<>();
    }

    /**
     * Gets the java line
     * @return The java line
     */
    @Override
    public String getAsJavaLine() {
        return this.entry.getName();
    }
}
