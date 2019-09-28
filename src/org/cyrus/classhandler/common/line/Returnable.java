package org.cyrus.classhandler.common.line;

import org.cyrus.classhandler.common.classtype.CommonClass;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface Returnable{

    Optional<CommonClass<? extends CommonClass>> getReturn();

    default Set<Callable> getNext(){
        return getNext(Callable.class, m -> true);
    }

    default Set<Callable> getNext(Predicate<Callable> filter){
        return this.getNext(Callable.class, filter);
    }

    default <C extends Callable> Set<C> getNext(Class<C> class1, Predicate<C> filter){
        Optional<CommonClass<? extends CommonClass>> opClass = getReturn();
        if(!opClass.isPresent()){
            return new HashSet<>();
        }
        return opClass.get().getCalls(class1).stream().filter(filter).collect(Collectors.toSet());
    }


}
