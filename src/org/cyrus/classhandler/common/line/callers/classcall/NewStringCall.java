package org.cyrus.classhandler.common.line.callers.classcall;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Caller;
import org.cyrus.classhandler.common.line.Writable;
import org.cyrus.classhandler.java.classtype.primitive.ZeroPackageJavaClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Caller for String
 */
public class NewStringCall<C extends CommonClass> implements Caller<ZeroPackageJavaClass.StringClass, C>, Writable {

    protected String value;
    protected  C attached;

    public NewStringCall(C attached){
        this(attached, "");
    }

    public NewStringCall(C clazz, String value){
        this.attached = clazz;
        this.value = value;
    }

    /**
     * sets the string
     * @param value The new String
     * @return itself for chaining
     */
    public NewStringCall setValue(String value){
        this.value = value;
        return this;
    }

    /**
     * gets the String value
     * @return The String for this caller
     */
    public String getValue(){
        return this.value;
    }

    /**
     * Gets the attached class
     * @return The String class
     */
    @Override
    public ZeroPackageJavaClass.StringClass getCallable() {
        return ZeroPackageJavaClass.STRING;
    }

    /**
     * Primitive numbers can not be written incorrectly
     * @return true
     */
    @Override
    public boolean isWrittenCorrectly() {
        return true;
    }

    /**
     * Primitive numbers can not be written incorrectly
     * @return A empty optional
     */
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
     * No imports for primitive class
     * @return an empty array list
     */
    @Override
    public List<CommonClass<? extends CommonClass>> getImports() {
        return new ArrayList<>();
    }

    /**
     * Special call required.
     * @return The java init for Strings
     */
    @Override
    public String getAsJavaLine() {
        return "\"" + this.value + "\"";
    }
}
