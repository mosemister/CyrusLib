package org.cyrus.classhandler.java.classtype;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.classtype.EnumClass;
import org.cyrus.classhandler.common.function.constructor.Constructor;
import org.cyrus.classhandler.common.line.Caller;
import org.cyrus.classhandler.common.line.EnumEntry.EnumEntry;
import org.cyrus.classhandler.common.line.callers.EnumEntryCall;
import org.cyrus.classhandler.java.function.constructor.JConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EnumJavaClass<T extends EnumJavaClass> extends CommonJavaClass<T> implements EnumClass<T> {

    public static class EnumJavaEntry implements EnumEntry {

        protected Enum entry;

        private EnumJavaEntry(Enum entry){
            this.entry = entry;
        }

        @Override
        public EnumClass<? extends EnumClass> getAttachedClass() {
            return new EnumJavaClass<>(this.entry.getDeclaringClass());
        }

        @Override
        public String getName() {
            return this.entry.name();
        }

        @Override
        public Caller getCaller() {
            return new EnumEntryCall(this);
        }
    }

    public static class GenericEnumJavaClass<T extends GenericEnumJavaClass> extends EnumJavaClass<T> implements CommonClass.AppliedGenerics<T> {

        protected String genericName;

        public GenericEnumJavaClass(String generic, Class<?> class1){
            super(class1);
            this.genericName = generic;
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

    public EnumJavaClass(Class<?> class1) {
        super(class1);
    }

    @Override
    public Set<EnumEntry> getEntries() {
        Set<EnumEntry> set = new HashSet<>();
        for(Object enumEntry : this.class1.getEnumConstants()){
            set.add(new EnumJavaEntry((Enum)enumEntry));
        }
        return set;
    }

    @Override
    public List<Constructor<T>> getConstructors() {
        java.lang.reflect.Constructor<?>[] cons = this.class1.getConstructors();
        List<Constructor<T>> list = new ArrayList<>();
        for(java.lang.reflect.Constructor<?> con : cons){
            list.add(new JConstructor(this, con));
        }
        return list;
    }

    @Override
    public String getClassType() {
        return CommonClass.ENUM;
    }

    @Override
    public AppliedGenerics toAppliedGenerics(String name) {
        return new EnumJavaClass.GenericEnumJavaClass<>(name, this.class1);
    }
}
