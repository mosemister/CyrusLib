package org.cyrus.classhandler.common.line.variable;

import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.callers.variable.FieldCaller;

/**
 * Field class
 */
public interface Field<X extends CommonClass> extends Variable<X> {

    /**
     * gets the Visibility
     * @return The visibility of the field
     */
    Visibility getVisibility();

    /**
     * Checks if the field is static
     * @return is static
     */
    boolean isStatic();

    /**
     * Creates a new instance of a field caller
     * @return new instance of a field caller
     */
    @Override
    default FieldCaller createCaller(CommonClass<? extends CommonClass> clazz){
        return new FieldCaller(clazz,this);
    }


    /**
     * Adds visibility and static to the variable if needed
     * @return field as it would be written inside a class
     */
    @Override
    default String getAsJavaLine(){
        String ret = this.getVisibility().getJavaLine();
        if(this.isStatic()){
            ret = ret + "static ";
        }
        return ret + Variable.super.getAsJavaLine();
    }
    /**
     * base class for writable field
     */
    interface Writable<X extends CommonClass> extends Field<X>, Variable.Writable<X>{

        /**
         * Sets the visibility of the field
         * @param vis the new visibility
         * @return itself for chaining
         */
        Field.Writable<X> setVisibility(Visibility vis);

        /**
         * Sets if the field is static
         * @param tatic if the field is static
         * @return itself for chaining
         */
        Field.Writable<X> setStatic(boolean tatic);

    }

    /**
     * base class for attachable fields
     */
    interface Attachable<X extends CommonClass> extends Field<X>, Variable.Attachable<X>{

        /**
         * Adds visibility and static to the variable if needed
         * @return field as it would be written inside a class
         */
        @Override
        default String getAsJavaLine(){
            String ret = this.getVisibility().getJavaLine();
            if(this.isStatic()){
                ret = ret + "static ";
            }
            return ret + Variable.Attachable.super.getAsJavaLine();
        }

    }
}
