package org.cyrus.classhandler.custom.function.method.cmethod;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.function.method.cmethod.StandardClassMethod;
import org.cyrus.classhandler.custom.classtype.StandardCustomClass;
import org.cyrus.classhandler.custom.function.method.CustomMethod;

public class StandardCustomClassMethod<C extends StandardCustomClass> extends CustomMethod<C> implements StandardClassMethod<C> {

    public static class StandardMethodBuilder<C extends StandardCustomClass> extends CustomMethod.CustomMethodBuilder<C> {

        @Override
        public StandardMethodBuilder<C> setReturn(CommonClass<? extends CommonClass> clazz){
            return (StandardMethodBuilder<C>) super.setReturn(clazz);
        }

        @Override
        public StandardMethodBuilder<C> setAttachedClass(C clazz){
            return (StandardMethodBuilder)super.setAttachedClass(clazz);
        }

        @Override
        public StandardMethodBuilder<C> setName(String name){
            return (StandardMethodBuilder)super.setName(name);
        }

        @Override
        public StandardCustomClassMethod<C> build() {
            if(this.name == null){
                System.err.println("Name must be set");
                return null;
            }
            for(int A = 0; A < this.name.length(); A++){
                if(!Character.isLetterOrDigit(this.name.charAt(A))){
                    System.err.println("Name can only have letters and digit");
                    return null;
                }
            }
            if(Character.isUpperCase(this.name.charAt(0))){
                System.err.println("First letter must be lower case");
                return null;
            }
            if(this.attached == null){
                System.err.println("The attached value must be set");
                return null;
            }
            StandardCustomClassMethod<C> method = new StandardCustomClassMethod<>(this.attached, this.name);
            method.setStatic(this.isStatic);
            method.setVisibility(this.visibility);
            method.setReturn(this.returnClass);
            this.attached.getMethods().add(method);
            return method;
        }
    }

    public StandardCustomClassMethod(C attached, String name) {
        super(attached, name);
    }
}
