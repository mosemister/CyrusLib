package org.cyrus.classhandler.common.line.callers.classcall;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Caller;
import org.cyrus.classhandler.common.line.Writable;
import org.cyrus.classhandler.java.classtype.primitive.ZeroPackageJavaClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Creates a call to a new instance of a boolean value
 */
public class NewBooleanCall<C extends CommonClass> implements Caller<ZeroPackageJavaClass.JavaPrimitiveClass.BooleanClass, C>, Writable {

    protected boolean value;
    protected C attached;

    /**
     * defaults to false
     */
    public NewBooleanCall(C clazz){
        this(clazz, false);
    }

    /**
     *
     * @param value The value that the boolean should be
     */
    public NewBooleanCall(C clazz, boolean value){
        this.value = value;
        this.attached = clazz;
    }

    /**
     * gets the call as a boolean value
     * @return The boolean value of this call
     */
    public boolean getBoolean(){
        return this.value;
    }

    /**
     * gets the attached callable
     * @return the caller for this
     */
    @Override
    public ZeroPackageJavaClass.JavaPrimitiveClass.BooleanClass getCallable() {
        return ZeroPackageJavaClass.BOOLEAN;
    }

    /**
     * Checks if the class has all values attached
     * @return true, this can not be written incorrectly
     */
    @Deprecated
    @Override
    public boolean isWrittenCorrectly() {
        return true;
    }

    /**
     * No errors can occur with this class
     * @return Optional.empty()
     */
    @Deprecated
    @Override
    public Optional<String> getDescriptionOfError() {
        return Optional.empty();
    }

    /**
     * Gets the attached class.
     * @return the class this call is used in
     */
    @Override
    public C getAttachedClass() {
        return this.attached;
    }

    /**
     * No imports for this class
     * @return a blank array list
     */
    @Override
    public List<CommonClass<? extends CommonClass>> getImports() {
        return new ArrayList<>();
    }

    /**
     * Special call logic required for this class.
     * @return
     */
    @Override
    public String getAsJavaLine() {
        return value + "";
    }
}
