package org.cyrus.classhandler.common.line.callers.function;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.function.constructor.Constructor;
import org.cyrus.util.ArrayUtils;

import java.util.Arrays;
import java.util.List;

/**
 * A caller for constructors
 * @param <C> Attached class
 */
public class ConstructorCall<C extends CommonClass> extends FunctionCall<Constructor<? extends CommonClass>, C> {

    public ConstructorCall(C clazz, Constructor<? extends CommonClass> function) {
        super(clazz, function);
    }

    /**
     * Gets the import of the functions init attached class
     * @return The attached class of the function
     */
    @Override
    public List<CommonClass<? extends CommonClass>> getImports(){
        return Arrays.asList(this.function.getAttachedClass());
    }

    /**
     * Adds 'new' to the default caller
     * @return Java line for constructor calls
     */
    @Override
    public String getAsJavaLine() {
        return "new " + super.getAsJavaLine();
    }
}
