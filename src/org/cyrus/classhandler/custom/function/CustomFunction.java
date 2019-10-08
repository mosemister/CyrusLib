package org.cyrus.classhandler.custom.function;

import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.classtype.AnnotationClass;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.classtype.GenericList;
import org.cyrus.classhandler.common.function.Function;
import org.cyrus.classhandler.common.line.variable.Parameter;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomFunction <C extends CommonClass> implements Function<C> {

    protected C attachedClass;
    protected List<Parameter<? extends CommonClass>> parameters = new ArrayList<>();
    protected List<AnnotationClass<? extends AnnotationClass>> annotations = new ArrayList<>();
    protected Visibility visibility = Visibility.PUBLIC;
    protected GenericList generics = new GenericList();

    public CustomFunction(C attached){
        this.attachedClass = attached;
    }

    @Override
    public C getAttachedClass() {
        return this.attachedClass;
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
    public Visibility getVisibility() {
        return this.visibility;
    }

    @Override
    public List<AnnotationClass<? extends AnnotationClass>> getAnnotations(){
        return this.annotations;
    }

    @Override
    public List<Parameter<? extends CommonClass>> getParameters() {
        return this.parameters;
    }

}
