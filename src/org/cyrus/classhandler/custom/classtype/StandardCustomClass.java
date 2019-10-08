package org.cyrus.classhandler.custom.classtype;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.classtype.StandardClass;
import org.cyrus.classhandler.common.function.constructor.Constructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StandardCustomClass <C extends StandardCustomClass> extends AbstractCommonCustomClass<C> implements StandardClass.Writable<C> {

    public static class StandardClassBuilder extends CustomClassBuilder.AbstractCustomClassBuilder<StandardCustomClass> {

        boolean isAbstract;
        boolean isStatic;
        StandardClass<? extends StandardClass> extend;

        public CustomClassBuilder setAbstract(boolean isAbstract){
            this.isAbstract = isAbstract;
            return this;
        }

        public CustomClassBuilder setStatic(boolean static1){
            this.isStatic = static1;
            return this;
        }

        public CustomClassBuilder setExtends(StandardClass<? extends StandardClass> class1){
            this.extend = class1;
            return this;
        }

        @Override
        public StandardCustomClass<StandardCustomClass> build() {
            StandardCustomClass<StandardCustomClass> scc = new StandardCustomClass<>(this.name, this.package1);
            scc.setAbstract(this.isAbstract);
            scc.setName(this.name);
            scc.setVisibility(this.visibility);
            scc.setStatic(this.isStatic);
            if(this.extend != null){
                scc.setExtends(this.extend);
            }
            scc.getImplements().addAll(this.interfaces);
            return scc;
        }

    }

    public static class GenericStandardCustomClass extends StandardCustomClass implements CommonClass.AppliedGenerics {

        protected String generic;

        public GenericStandardCustomClass(String generic, String name, String... package1) {
            super(name, package1);
            this.generic = generic;
        }

        @Override
        public String getDisplayName(){
            return this.generic;
        }

        @Override
        public String getOriginalDisplayName() {
            return this.getDisplayName();
        }
    }

    protected boolean isAbstract;
    protected StandardClass extends1;
    protected List<Constructor<C>> constructors = new ArrayList<>();

    protected StandardCustomClass(String name, String... package1) {
        super(name, package1);
    }

    @Override
    public boolean isAbstract() {
        return this.isAbstract;
    }

    @Override
    public List<Constructor<C>> getConstructors() {
        return this.constructors;
    }

    @Override
    public Optional<StandardClass> getExtends() {
        return Optional.ofNullable(this.extends1);
    }

    @Override
    public StandardCustomClass setAbstract(boolean check) {
        this.isAbstract = check;
        return this;
    }

    @Override
    public StandardClass.Writable setExtends(StandardClass class1) {
        this.extends1 = class1;
        return this;
    }

    @Override
    public String getClassType() {
        return CommonClass.CLASS;
    }

    @Override
    public boolean isInstanceOf(CommonClass class1) {
        return false;
    }

    @Override
    public AppliedGenerics toAppliedGenerics(String name) {
        return new StandardCustomClass.GenericStandardCustomClass(name, getName(), getPackage());
    }
}
