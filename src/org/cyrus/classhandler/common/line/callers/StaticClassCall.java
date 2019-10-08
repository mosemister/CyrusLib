package org.cyrus.classhandler.common.line.callers;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Callable;
import org.cyrus.classhandler.common.line.Caller;
import org.cyrus.classhandler.common.line.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Call for classes
 */
public class StaticClassCall implements Caller, Writable {

    protected CommonClass class1;
    protected CommonClass<? extends CommonClass> attached;

    public StaticClassCall(CommonClass<? extends CommonClass> clazz, CommonClass class1){
        this.attached = clazz;
        this.class1 = class1;
    }

    /**
     * Gets the java line
     * @return line for java
     */
    @Override
    public String getAsJavaLine() {
        return this.class1.getName();
    }

    /**
     * Can not write incorrectly
     * @return true
     */
    @Override
    public boolean isWrittenCorrectly() {
        return true;
    }

    /**
     * Can not write incorrectly
     * @return optional empty
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
    public CommonClass<? extends CommonClass> getAttachedClass() {
        return this.attached;
    }

    /**
     * gets the imports required for this call to work
     * @return Gets all imports
     */
    @Override
    public List<CommonClass<? extends CommonClass>> getImports() {
        List<CommonClass<? extends CommonClass>> list = new ArrayList<>();
        list.add(this.class1);
        return list;
    }

    /**
     * Gets the attached caller
     * @return the class it is calling to
     */
    @Override
    public Callable getCallable() {
        return this.class1;
    }
}
