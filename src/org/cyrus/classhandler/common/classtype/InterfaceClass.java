package org.cyrus.classhandler.common.classtype;

/**
 * base class for all interface classes
 * @param <C> itself
 */
public interface InterfaceClass<C extends InterfaceClass> extends CommonClass<C> {

    @Override
    default boolean isInstanceOf(CommonClass class1) {
        return getImplements().stream().anyMatch(c -> {
            if(class1.isMatch(c)){
                return true;
            }
            if(class1.isInstanceOf(c)){
                return true;
            }
            return false;
        });
    }

}
