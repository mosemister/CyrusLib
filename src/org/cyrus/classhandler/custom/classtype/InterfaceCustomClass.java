package org.cyrus.classhandler.custom.classtype;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.classtype.InterfaceClass;

public class InterfaceCustomClass <C extends InterfaceCustomClass> extends AbstractCommonCustomClass<C> implements InterfaceClass<C> {

    public static class InterfaceClassBuilder extends CustomClassBuilder.AbstractCustomClassBuilder<InterfaceCustomClass> implements CustomClassBuilder<InterfaceCustomClass>{

        @Override
        public InterfaceCustomClass build() {
            InterfaceCustomClass icc = new InterfaceCustomClass(this.name, this.package1);
            icc.getImplements().addAll(this.interfaces);
            icc.setName(this.name);
            icc.setVisibility(this.visibility);
            return icc;
        }
    }

    public static class GenericInterfaceCustomClass extends InterfaceCustomClass<GenericInterfaceCustomClass> implements CommonClass.AppliedGenerics<GenericInterfaceCustomClass>{

        protected String name;

        public GenericInterfaceCustomClass(String generic, String name, String... package1) {
            super(name, package1);
            this.name = generic;
        }

        public String getDisplayName(){
            return this.name;
        }

        @Override
        public String getOriginalDisplayName() {
            return super.getDisplayName();
        }
    }

    public InterfaceCustomClass(String name, String... package1) {
        super(name, package1);
    }

    @Override
    public String getClassType() {
        return CommonClass.INTERFACE;
    }

    @Override
    public AppliedGenerics toAppliedGenerics(String name) {
        return new GenericInterfaceCustomClass(name, this.getName(), this.getPackage());
    }
}
