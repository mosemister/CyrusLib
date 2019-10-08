package org.cyrus.classhandler.common.line.callers.classcall;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Caller;
import org.cyrus.classhandler.common.line.Writable;
import org.cyrus.classhandler.java.classtype.AbstractCommonJavaClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NewNumberCall<C extends CommonClass, N extends Number> implements Caller<CommonClass.PrimitiveClass, C>, Writable {

    protected N value;
    protected CommonClass.PrimitiveClass ret;
    protected C attached;

    /**
     * The number must be a primitive class as the caller will not be written correctly otherwise
     * @param number the represented value of this call
     */
    public NewNumberCall(C clazz, CommonClass.PrimitiveClass ret, N number){
        this.attached = clazz;
        this.value = number;
        this.ret = ret;
    }

    /**
     * Gets the represented value
     * @return Gets the value for this cal;
     */
    public N getNumber(){
        return value;
    }

    /**
     * Gets the attached class
     * @return Gets the class of the number
     */
    @Override
    public AbstractCommonJavaClass.PrimitiveClass getCallable() {
        return this.ret;
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
     * Gets the Java code
     * @return code for the caller
     */
    @Override
    public String getAsJavaLine() {
        return this.value.toString();
    }
}
