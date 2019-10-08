package org.cyrus.classhandler.common.line.callers.classcall;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.line.Caller;
import org.cyrus.classhandler.common.line.Writable;
import org.cyrus.classhandler.java.classtype.primitive.ZeroPackageJavaClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * class for char calls
 */
public class NewCharacterCall<C extends CommonClass> implements Caller<ZeroPackageJavaClass.JavaPrimitiveClass.CharacterClass, C>, Writable {

    protected char value;
    protected C attached;

    /**
     * @param value The char to be represented
     */
    public NewCharacterCall(C clazz, char value){
        this.attached = clazz;
        this.value = value;
    }

    /**
     * @return The represented character
     */
    public char getCharacter(){
        return this.value;
    }

    /**
     * @return The char class
     */
    @Override
    public ZeroPackageJavaClass.JavaPrimitiveClass.CharacterClass getCallable() {
        return ZeroPackageJavaClass.CHAR;
    }

    /**
     * This class can not be written incorrectly
     * @return true
     */
    @Deprecated
    @Override
    public boolean isWrittenCorrectly() {
        return true;
    }

    /**
     * This class can not be written incorrectly
     * @return empty optional
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
     * No imports required
     * @return a blank array list
     */
    @Override
    public List<CommonClass<? extends CommonClass>> getImports() {
        return new ArrayList<>();
    }

    /**
     * Special caller required
     * @return the java caller
     */
    @Override
    public String getAsJavaLine() {
        return "'" + this.value + "'";
    }
}
