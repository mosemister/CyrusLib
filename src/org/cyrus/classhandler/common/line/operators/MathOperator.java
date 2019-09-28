package org.cyrus.classhandler.common.line.operators;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.classtype.StandardClass;
import org.cyrus.classhandler.common.line.Caller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MathOperator implements Operator{

    public static final char PLUS = '+';
    public static final char MULTIPLY = '*';
    public static final char DIVIDE = '/';
    public static final char MINUS = '-';

    protected char operator;
    protected List<Caller> list = new ArrayList<>();

    public MathOperator(char equ, Caller store1, Caller store2){
        this.operator = equ;
        this.list.add(store1);
        this.list.add(store2);
    }

    public Caller getLeftAttached(){
        return this.list.get(0);
    }

    public Caller getRightAttached(){
        return this.list.get(1);
    }

    @Override
    public List<Caller> getAttached() {
        return this.list;
    }

    @Override
    public int getMaxAttachments() {
        return 2;
    }

    @Override
    public boolean isWrittenCorrectly() {
        switch (this.operator){
            case PLUS:
            case MINUS:
            case MULTIPLY:
            case DIVIDE: break;
            default: return false;
        }
        for(Caller caller : this.list){
            if(!(caller.getReturn().get().isInstanceOf(null))){//check number
                return false;
            }
            if(!(caller.getReturn().get() instanceof StandardClass.PrimitiveClass)){
                return false;
            }
        }
        return true;
    }

    @Override
    public Optional<String> getDescriptionOfError() {
        switch (this.operator){
            case PLUS:
            case MINUS:
            case MULTIPLY:
            case DIVIDE: break;
            default: Optional.of("Unknown maths");
        }
        for(Caller caller : this.list){
            if(!(caller.getReturn().get().isInstanceOf(null))){//check number
                return Optional.of("Value 1 is not a primitive number");
            }
            if(!(caller.getReturn().get() instanceof StandardClass.PrimitiveClass)){
                return Optional.of("Value 2 is not a primitive number");
            }
        }
        return Optional.empty();
    }

    @Override
    public String getAsJavaLine() {
        return getLeftAttached().getCallable().getName() + " " + this.operator + " " + getRightAttached();
    }

    @Override
    public Optional<CommonClass<? extends CommonClass>> getReturn() {
        //TODO
        return Optional.empty();
    }
}
