package org.cyrus.classhandler.common.classtype;

import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.function.method.Method;

import java.util.ArrayList;
import java.util.List;

public abstract class ArrayClass<A extends CommonClass, C extends ArrayClass> implements CommonClass<C> {

    A clazz;

    public ArrayClass(A clazz){
        this.clazz = clazz;
    }

    public A getNoneArrayClass(){
        return this.clazz;
    }

    @Override
    public boolean isStatic() {
        return false;
    }

    @Override
    public Visibility getVisibility() {
        return Visibility.PUBLIC;
    }

    @Override
    public String[] getPackage() {
        return this.clazz.getPackage();
    }

    @Override
    public String getClassType() {
        return this.clazz.getClassType();
    }

    @Override
    public List<? extends InterfaceClass> getImplements() {
        return new ArrayList<>();
    }

    @Override
    public List<? extends Nested> getNestedClasses() {
        return new ArrayList<>();
    }

    @Override
    public boolean isInstanceOf(CommonClass class1) {
        return this.clazz.isInstanceOf(class1);
    }

    @Override
    public List<? extends Method<C>> getMethods() {
        return new ArrayList<>();
    }

    @Override
    public List<AnnotationClass<? extends AnnotationClass>> getAnnotations() {
        return new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.clazz.getName() + "[]";
    }

}
