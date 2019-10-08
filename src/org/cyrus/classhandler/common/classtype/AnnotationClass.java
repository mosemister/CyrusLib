package org.cyrus.classhandler.common.classtype;

import java.util.ArrayList;
import java.util.List;

/**
 * A base class of all Annotation classes
 * @param <T> Itself
 */
public interface AnnotationClass<T extends AnnotationClass> extends CommonClass<T> {

    /**
     * Checks if the annotation is instance of another class
     * @param class1 the class being compared to
     * @return if the class is an instance then returns true, if not then returns false
     */
    @Override
    default boolean isInstanceOf(CommonClass class1) {
        if(!(class1 instanceof AnnotationClass)){
            return false;
        }
        return class1.getAnnotations().stream().anyMatch(c -> c.equals(AnnotationClass.this));
    }

    /**
     *
     * @return a blank list
     * @Deprecated An annotation can not extend an interface
     */
    @Override
    @Deprecated
    default List<InterfaceClass> getImplements(){
        return new ArrayList<>();
    }
}
