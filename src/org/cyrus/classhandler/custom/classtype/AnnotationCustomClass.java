package org.cyrus.classhandler.custom.classtype;

import org.cyrus.classhandler.common.appliable.AnnotationAppliable;
import org.cyrus.classhandler.common.classtype.AnnotationClass;
import org.cyrus.classhandler.common.classtype.CommonClass;

public class AnnotationCustomClass<C extends AnnotationCustomClass, A extends AnnotationAppliable> extends CommonCustomClass<C> implements AnnotationClass<C> {

    public static class GenericAnnotationCustomClass<A extends AnnotationAppliable> extends AnnotationCustomClass<GenericAnnotationCustomClass<A>, A> implements CommonClass.AppliedGenerics<GenericAnnotationCustomClass<A>>{

        protected String displayName;

        public GenericAnnotationCustomClass(A attached, String display, String name, String... package1) {
            super(attached, name, package1);
            this.displayName = display;
        }

        @Override
        public String getDisplayName(){
            return this.displayName;
        }

        @Override
        public String getOriginalDisplayName() {
            return this.name;
        }
    }

    protected A attached;

    public AnnotationCustomClass(A attached, String name, String... package1) {
        super(name, package1);
        this.attached = attached;
    }

    @Override
    public String getClassType() {
        return CommonClass.ANNOTATION;
    }

    @Override
    public GenericAnnotationCustomClass<A> toAppliedGenerics(String name) {
        return new GenericAnnotationCustomClass<>(this.attached, name, this.name, this.package1);
    }

}
