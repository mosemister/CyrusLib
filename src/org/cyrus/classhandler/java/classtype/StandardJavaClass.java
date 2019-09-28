package org.cyrus.classhandler.java.classtype;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.classtype.StandardClass;
import org.cyrus.classhandler.common.function.constructor.Constructor;
import org.cyrus.classhandler.java.function.constructor.JConstructor;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StandardJavaClass<C extends StandardJavaClass> extends CommonJavaClass<C> implements StandardClass<C> {

    public static class GenericStandardJavaClass extends StandardJavaClass<GenericStandardJavaClass> implements CommonClass.AppliedGenerics<GenericStandardJavaClass>{

        protected String genericName;

        public GenericStandardJavaClass(String name, Class<?> class1) {
            super(class1);
            this.genericName = name;
        }

        @Override
        public String getDisplayName(){
            return this.genericName;
        }

        @Override
        public String getOriginalDisplayName() {
            return super.getDisplayName();
        }
    }

    public StandardJavaClass(Class<?> class1) {
        super(class1);
    }

    @Override
    public boolean isAbstract() {
        return Modifier.isAbstract(this.class1.getModifiers());
    }

    @Override
    public List<Constructor<C>> getConstructors() {
        java.lang.reflect.Constructor<?>[] cons = this.class1.getConstructors();
        List<Constructor<C>> list = new ArrayList<>();
        for(java.lang.reflect.Constructor<?> con : cons){
            list.add(new JConstructor(this, con));
        }
        return list;
    }

    @Override
    public Optional<StandardClass> getExtends() {
        Class<? extends Object> class1 = this.class1.getSuperclass();
        if(class1 == null || (class1.isAssignableFrom(Object.class) && this.class1.isAssignableFrom(Object.class))){
            return Optional.empty();
        }
        return Optional.of((StandardClass) CommonJavaClass.of(class1));
    }

    @Override
    public String getClassType() {
        return CommonClass.CLASS;
    }

    @Override
    public AppliedGenerics toAppliedGenerics(String name) {
        return new GenericStandardJavaClass(name, this.class1);
    }
}
