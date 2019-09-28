package org.cyrus.classhandler.common.classtype;

import org.cyrus.classhandler.common.function.constructor.Constructor;
import org.cyrus.classhandler.common.line.Callable;
import org.cyrus.classhandler.common.line.EnumEntry.EnumEntry;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface EnumClass<C extends EnumClass> extends CommonClass<C> {

    Set<EnumEntry> getEntries();
    List<Constructor<C>> getConstructors();

    @Override
    default Set<Callable> getCalls() {
        Set<Callable> set = new HashSet<>();
        set.addAll(this.getFields());
        set.addAll(this.getMethods());
        return set;
    }


}
