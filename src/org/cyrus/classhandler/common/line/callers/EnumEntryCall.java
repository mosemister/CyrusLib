package org.cyrus.classhandler.common.line.callers;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Callable;
import org.cyrus.classhandler.common.line.Caller;
import org.cyrus.classhandler.common.line.EnumEntry.EnumEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnumEntryCall implements Caller {

    protected EnumEntry entry;

    public EnumEntryCall(EnumEntry entry) {
        this.entry = entry;
    }

    @Override
    public Callable getCallable() {
        return this.entry;
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
        return this.entry.getAttachedClass();
    }

    @Override
    public List<CommonClass<? extends CommonClass>> getImports() {
        return new ArrayList<>();
    }

    @Override
    public String getAsJavaLine() {
        return this.entry.getName();
    }
}
