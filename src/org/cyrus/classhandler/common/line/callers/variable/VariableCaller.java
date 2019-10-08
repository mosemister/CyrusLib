package org.cyrus.classhandler.common.line.callers.variable;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Caller;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.Writable;
import org.cyrus.classhandler.common.line.variable.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VariableCaller<V extends Variable, X extends CommonClass> implements Caller<V, X>, Writable {

    protected V variable;
    protected CommonClass<X> attached;

    public VariableCaller(CommonClass<X> clazz, V variable){
        this.attached = clazz;
        this.variable = variable;
    }

    /**
     * Gets the init of the caller
     * @return The variable init
     */
    @Override
    public V getCallable() {
        return this.variable;
    }

    /**
     * variable call can not be written incorrectly
     * @return true
     */
    @Override
    public boolean isWrittenCorrectly() {
        return true;
    }

    /**
     * variable can not be written incorrectly
     * @return Optional.empty
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
    public CommonClass<X> getAttachedClass() {
        return this.attached;
    }

    /**
     * No Imports needed for variable
     * @return a blank array list
     */
    @Override
    public List<CommonClass<? extends CommonClass>> getImports() {
        return new ArrayList<>();
    }

    /**
     * the variables name
     * @return the name of the variable
     */
    @Override
    public String getAsJavaLine() {
        return this.variable.getName();
    }

}
