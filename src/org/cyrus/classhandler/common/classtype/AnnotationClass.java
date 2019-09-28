package org.cyrus.classhandler.common.classtype;

import org.cyrus.classhandler.common.appliable.AnnotationAppliable;

import java.util.ArrayList;
import java.util.List;

public interface AnnotationClass<T extends AnnotationClass> extends CommonClass<T> {

    @Override
    default boolean isInstanceOf(CommonClass class1) {
        if(!(class1 instanceof AnnotationClass)){
            return false;
        }
        return class1.getAnnotations().stream().anyMatch(c -> c.equals(AnnotationClass.this));
    }

    @Override
    @Deprecated
    default List<InterfaceClass> getImplements(){
        return new ArrayList<>();
    }
}
