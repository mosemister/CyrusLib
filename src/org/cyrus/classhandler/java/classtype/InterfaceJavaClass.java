package org.cyrus.classhandler.java.classtype;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.classtype.InterfaceClass;

public class InterfaceJavaClass extends CommonJavaClass implements InterfaceClass {

    public static class GenericInterfaceJavaClass extends InterfaceJavaClass implements CommonClass.AppliedGenerics{

        protected String genericName;

        public GenericInterfaceJavaClass(String name, Class<?> class1) {
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

    public InterfaceJavaClass(Class<?> class1) {
        super(class1);
    }

    @Override
    public String getClassType() {
        return CommonClass.INTERFACE;
    }

    @Override
    public AppliedGenerics toAppliedGenerics(String name) {
        return new GenericInterfaceJavaClass(name, this.class1);
    }
}
