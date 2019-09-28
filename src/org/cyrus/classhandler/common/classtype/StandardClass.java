package org.cyrus.classhandler.common.classtype;

import org.cyrus.classhandler.common.function.constructor.Constructor;
import org.cyrus.classhandler.common.line.Callable;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StandardClass<C extends StandardClass> extends CommonClass<C> {

    boolean isAbstract();
    List<Constructor<C>> getConstructors();
    Optional<StandardClass> getExtends();

    @Override
    default Set<Callable> getCalls() {
        Set<Callable> set = new HashSet<>();
        set.addAll(this.getFields());
        set.addAll(this.getMethods());
        getExtends().ifPresent(e -> set.addAll(e.getCalls()));
        return set;
    }

    interface Writable <C extends StandardClass.Writable> extends StandardClass<C>, CommonClass.Writable<C>{

        StandardClass.Writable setAbstract(boolean check);
        StandardClass.Writable setExtends(StandardClass class1);

    }


}
