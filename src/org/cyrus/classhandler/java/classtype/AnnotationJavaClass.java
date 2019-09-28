package org.cyrus.classhandler.java.classtype;

import org.cyrus.classhandler.common.appliable.AnnotationAppliable;
import org.cyrus.classhandler.common.classtype.AnnotationClass;
import org.cyrus.classhandler.common.classtype.CommonClass;

import java.lang.annotation.Annotation;
import java.util.List;

public class AnnotationJavaClass <C extends AnnotationJavaClass> extends CommonJavaClass<C> implements AnnotationClass<C> {

    public AnnotationJavaClass(Class<?> class1) {
        super(class1);
    }

    public Class<? extends Annotation> getTargetClass(){
        return (Class<? extends Annotation>) super.getTargetClass();
    }

    @Override
    public String getClassType() {
        return CommonClass.ANNOTATION;
    }

    @Override
    public AppliedGenerics toAppliedGenerics(String name) {
        return null;
    }
}
