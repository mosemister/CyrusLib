package org.cyrus.classhandler.common.line.variable;

import org.cyrus.classhandler.common.classtype.ArrayClass;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.function.Function;

import java.util.Optional;

/**
 * Base class for parameters
 * @param <C> attached class
 */
public interface Parameter<C extends CommonClass> extends Variable<C> {

    /**
     * Gets the attached function
     * @return The attached function
     */
    Function<C> getAttachedFunction();

    /**
     * gets the attached class
     * @return the attached class
     */
    @Override
    default C getAttachedClass(){
        return getAttachedFunction().getAttachedClass();
    }

    /**
     * Parameters can be final without an attached value, otherwise it uses the default variable checks
     * @return true if the parameter is written correctly
     */
    @Override
    default boolean isWrittenCorrectly(){
        if(this.isFinal()){
            return true;
        }
        return Variable.super.isWrittenCorrectly();
    }

    /**
     * Parameters can be final without an attached value, otherwise it uses the default variable checks
     * @return the English message of if the parameter is written correctly
     */
    @Override
    default Optional<String> getDescriptionOfError(){
        if(this.isFinal()){
            return Optional.empty();
        }
        return Variable.super.getDescriptionOfError();
    }

    /**
     * Gets the default way to write variables in Java
     * @return the java line for variables
     */
    @Override
    default String getAsJavaLine() {
        CommonClass<? extends CommonClass> ret = this.getReturn().get();
        if(ret instanceof ArrayClass){
            return ((ArrayClass) ret).getNoneArrayClass().getName() + "... " + this.getName();
        }
        return ret.getName() + " " + this.getName();
    }

    /**
     * base class for writable parameters
     * @param <C> attached class
     */
    interface Writable<C extends CommonClass> extends Variable.Writable<C>, Parameter<C>{

        /**
         * Sets the return of the field
         * @param class1 the class to return
         * @return itself for chaining
         */
        Parameter.Writable<C> setReturn(CommonClass<? extends CommonClass> class1);

    }

}
