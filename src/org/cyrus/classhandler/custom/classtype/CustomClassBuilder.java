package org.cyrus.classhandler.custom.classtype;

import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.classtype.InterfaceClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public interface CustomClassBuilder<C extends AbstractCommonCustomClass> {

    CustomClassBuilder<C> setPackage(String... packa);
    CustomClassBuilder<C> setName(String name);
    CustomClassBuilder<C> setImplements(Collection<? extends InterfaceClass> collection);
    CustomClassBuilder<C> setVisibility(Visibility visibility);
    C build();

    default CustomClassBuilder setImplements(InterfaceClass... collection){
        return setImplements(Arrays.asList(collection));
    }

    abstract class AbstractCustomClassBuilder<C extends AbstractCommonCustomClass> implements CustomClassBuilder<C>{

        String[] package1;
        String name;
        Visibility visibility = Visibility.PUBLIC;
        List<? extends InterfaceClass> interfaces = new ArrayList<>();

        @Override
        public CustomClassBuilder setPackage(String... packa) {
            this.package1 = packa;
            return this;
        }

        @Override
        public CustomClassBuilder setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public CustomClassBuilder setVisibility(Visibility visibility) {
            this.visibility = visibility;
            return this;
        }

        @Override
        public CustomClassBuilder setImplements(Collection collection) {
            this.interfaces.addAll(collection);
            return this;
        }

    }
}
