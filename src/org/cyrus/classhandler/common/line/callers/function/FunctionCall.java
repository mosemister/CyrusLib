package org.cyrus.classhandler.common.line.callers.function;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.function.Function;
import org.cyrus.classhandler.common.line.Callable;
import org.cyrus.classhandler.common.line.Caller;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.Writable;
import org.cyrus.classhandler.common.line.variable.Parameter;
import org.cyrus.util.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * base caller for functions
 * @param <C> the attached class
 */
public abstract class FunctionCall<F extends Function<? extends CommonClass>, C extends CommonClass> implements Caller<F, C>, Writable {

    protected F function;
    protected List<Line<C>> parameters = new ArrayList<>();
    protected C attached;

    public FunctionCall(C clazz, F function){
        this.attached = clazz;
        this.function = function;
    }

    /**
     * Attached lines to make up the parameters of the function
     * @return A list of values for the parameters
     */
    public List<Line<C>> getParameters(){
        return this.parameters;
    }

    /**
     * Gets the attached function
     * @return The attached function
     */
    @Override
    public F getCallable() {
        return this.function;
    }

    /**
     * Checks that parameters found in the function call match the
     * parameters found in the function itself.
     * @return if the parameters matched
     */
    @Override
    public boolean isWrittenCorrectly() {
        for(int A = 0; A < this.function.getParameters().size(); A++){
            if(this.parameters.size() <= A){
                return false;
            }
            Optional<CommonClass<? extends CommonClass>> rReturn = this.parameters.get(A).getReturn();
            if(!rReturn.isPresent()){
                return false;
            }
            Parameter<? extends CommonClass> parameter = this.function.getParameters().get(A);
            CommonClass<? extends CommonClass> pReturn = parameter.getReturn().get();
            if(!rReturn.get().isInstanceOf(pReturn)){
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the error message if the call is not correctly written
     * @return The error message
     */
    @Override
    public Optional<String> getDescriptionOfError() {
        for(int A = 0; A < this.function.getParameters().size(); A++){
            if(this.parameters.size() <= A){
                return Optional.of("Too many parameters");
            }
            Parameter<? extends CommonClass> parameter = this.function.getParameters().get(A);
            Optional<CommonClass<? extends CommonClass>> rReturn = this.parameters.get(A).getReturn();
            if(!rReturn.isPresent()){
                return Optional.of("Argument " + (A + 1) + "(" + parameter.getName() + ") does not return anything");
            }
            CommonClass<? extends CommonClass> pReturn = parameter.getReturn().get();
            if(!rReturn.get().isInstanceOf(pReturn)){
                return Optional.of("Argument " + (A + 1) + "(" + parameter.getName() + ") does not return " + pReturn.getName());
            }
        }
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
     * Gets the return class of the function.
     * @return An Optional of the returning class for the function, if void then this will return Optional.empty
     */
    @Override
    public Optional<CommonClass<? extends CommonClass>> getReturn() {
        return this.function.getReturn();
    }

    /**
     * No Imports
     * @return An empty list
     */
    @Override
    public List<CommonClass<? extends CommonClass>> getImports(){
        return new ArrayList<>();
    }

    /**
     * Gets the call as a Java line
     * @return Java Line for the call
     */
    @Override
    public String getAsJavaLine() {
        return this.function.getName() + " (" + ArrayUtils.toString(", ", f -> f.getAsJavaLine(), this.parameters) + ")";
    }
}
