package org.cyrus.classhandler.common.line.variable;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Callable;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.Writable;
import org.cyrus.classhandler.common.line.callers.variable.VariableCaller;
import org.cyrus.util.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The variable class
 */
public interface Variable<X extends CommonClass> extends Callable, Line<X>, Writable {

    /**
     * Checks if the variable is a final variable
     * @return If it is a final variable
     */
    boolean isFinal();

    @Override
    default String[] getSplitName() {
        if (isFinal()) {
            return ArrayUtils.splitBy(getName(), 0, true, c -> c == '_');
        }
        return ArrayUtils.splitBy(getName(), 0, true, c -> Character.isUpperCase(c));
    }

    /**
     * Creates a new instanceof of a caller
     * @return New instance of a caller for this variable
     */
    @Override
    default VariableCaller createCaller(CommonClass<? extends CommonClass> clazz){
        return new VariableCaller(clazz, this);
    }

    /**
     * Gets all the imports of the variable. By default the return of the variable
     * is the only import, however attachable may require more
     * @return All imports for the variable
     */
    @Override
    default List<CommonClass<? extends CommonClass>> getImports(){
        List<CommonClass<? extends CommonClass>> list = new ArrayList<>();
        getReturn().ifPresent(c -> list.add(c));
        if(this instanceof Attachable){
            ((Attachable<X>) this).getAttachedLine().ifPresent(l -> list.addAll(l.getImports()));
        }
        return list;
    }

    /**
     * Gets the default way to write variables in Java
     * @return the java line for variables
     */
    @Override
    default String getAsJavaLine() {
        return this.getReturn().get().getName() + " " + this.getName();
    }

    /**
     * Gets the English error from a failed write
     * @return the error in English writing
     */
    @Override
    default Optional<String> getDescriptionOfError(){
            if(!(this instanceof Variable.Attachable)){
                if(this.isFinal()) {
                    return Optional.of("A final variable must have a value");
                }
                return Optional.empty();
            }
            Variable.Attachable attachable = (Variable.Attachable)this;
            if(!attachable.getAttachedLine().isPresent()){
                if(this.isFinal()) {
                    return Optional.of("A final variable must have a value");
                }
                return Optional.empty();
            }
            Optional<CommonClass<? extends CommonClass>> opReturn = ((Line<X>)attachable.getAttachedLine().get()).getReturn();
            if(!opReturn.isPresent()){
                return Optional.of("The attached line does not return anything");
            }
            Line<X> line = ((Line<X>)attachable.getAttachedLine().get());
            if(line.getReturn().get().isInstanceOf(this.getReturn().get())){
                if(line instanceof org.cyrus.classhandler.common.line.Writable){
                    return ((org.cyrus.classhandler.common.line.Writable) line).getDescriptionOfError();
                }
                return Optional.empty();
            }
            return Optional.of("The attached line does not match the variables class");
    }

    /**
     * Variables that can have a value attached to it
     */
    interface Attachable<X extends CommonClass> extends Variable<X> {

        /**
         * Gets the attached line
         * @return Gets the attached code to value the variable
         */
        Optional<Line<X>> getAttachedLine();

        /**
         * Gets the return of the variable
         * @return The class type of the value
         */
        @Override
        default Optional<CommonClass<? extends CommonClass>> getReturn() {
            Optional<Line<X>> opLine = getAttachedLine();
            if (!opLine.isPresent()) {
                return Optional.empty();
            }
            return opLine.get().getReturn();
        }

        /**
         * Runs the variable function and then adds on the attached line if present
         * @return the Java line version of this variable
         */
        @Override
        default String getAsJavaLine(){
            String superValue = Variable.super.getAsJavaLine();
            Optional<Line<X>> opAttached = getAttachedLine();
            if(opAttached.isPresent()){
                superValue = superValue + " = " + opAttached.get().getAsJavaLine();
            }
            return superValue;
        }
    }

    /**
     * Writable variable
     */
    interface Writable<X extends CommonClass> extends Variable<X> {

        /**
         * Sets the name of the variable
         * @param name The new name
         * @return itself for chaining
         */
        Writable<X> setName(String name);

        /**
         * Sets if the variable is final
         * @param inal if the variable should be final or not
         * @return itself for chaining
         */
        Writable<X> setFinal(boolean inal);

    }
}
