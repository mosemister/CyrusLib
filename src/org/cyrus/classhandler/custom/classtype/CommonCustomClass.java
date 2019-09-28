package org.cyrus.classhandler.custom.classtype;

import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.classtype.AnnotationClass;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.classtype.GenericList;
import org.cyrus.classhandler.common.classtype.InterfaceClass;
import org.cyrus.classhandler.common.function.method.Method;
import org.cyrus.classhandler.common.line.variable.Field;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonCustomClass<C extends CommonCustomClass> implements CommonClass.Writable<C> {

    protected boolean isStatic;
    protected Visibility visibility = Visibility.PUBLIC;
    protected String[] package1;
    protected String name;
    protected List<InterfaceClass> interfaces = new ArrayList<>();
    protected List<CommonClass.Nested> nestedClasses = new ArrayList<>();
    protected List<Field> fields = new ArrayList<>();
    protected List<Method<C>> methods = new ArrayList<>();
    protected GenericList generics = new GenericList();
    protected List<AnnotationClass<? extends AnnotationClass>> annotations = new ArrayList<>();

    public CommonCustomClass(String name, String... package1){
        this.name = name;
        this.package1 = package1;
    }

    @Override
    public boolean isStatic() {
        return this.isStatic;
    }

    @Override
    public Visibility getVisibility() {
        return this.visibility;
    }

    @Override
    public String[] getPackage() {
        return this.package1;
    }

    @Override
    public Writable setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Writable setVisibility(Visibility visibility) {
        this.visibility = visibility;
        return this;
    }

    @Override
    public List<InterfaceClass> getImplements() {
        return this.interfaces;
    }

    @Override
    public List<CommonClass.Nested> getNestedClasses() {
        return this.nestedClasses;
    }

    @Override
    public List<Field> getFields() {
        return fields;
    }

    @Override
    public List<Method<C>> getMethods() {
        return this.methods;
    }

    @Override
    public CommonCustomClass setStatic(boolean tatic) {
        this.isStatic = tatic;
        return this;
    }

    @Override
    public CommonCustomClass setPackage(String... ackage) {
        this.package1 = ackage;
        return this;
    }

    @Override
    public boolean hasGenerics() {
        return !this.generics.isEmpty();
    }

    @Override
    public GenericList getGenerics() {
        return this.generics;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<AnnotationClass<? extends AnnotationClass>> getAnnotations() {
        return this.annotations;
    }
}
