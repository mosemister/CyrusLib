package org.cyrus.classhandler.java.classtype.primitive;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.java.classtype.StandardJavaClass;

public class ZeroPackageJavaClass<C extends ZeroPackageJavaClass> extends StandardJavaClass<C> {

    public static final JavaPrimitiveClass.IntegerClass INTEGER = new JavaPrimitiveClass.IntegerClass();
    public static final JavaPrimitiveClass.DoubleClass DOUBLE = new JavaPrimitiveClass.DoubleClass();
    public static final JavaPrimitiveClass.BooleanClass BOOLEAN = new JavaPrimitiveClass.BooleanClass();
    public static final JavaPrimitiveClass.CharacterClass CHAR = new JavaPrimitiveClass.CharacterClass();
    public static final JavaPrimitiveClass.FloatClass FLOAT = new JavaPrimitiveClass.FloatClass();
    public static final JavaPrimitiveClass.LongClass LONG = new JavaPrimitiveClass.LongClass();
    public static final ObjectClass OBJECT = new ObjectClass();
    public static final StringClass STRING = new StringClass();

    public static final JavaPrimitiveClass[] PRIMITIVE = {INTEGER, DOUBLE, BOOLEAN, CHAR, FLOAT, LONG};
    public static final ZeroPackageJavaClass[] ZERO_PACKAGE = {OBJECT, STRING};

    public static class ObjectClass extends ZeroPackageJavaClass<ObjectClass> {

        public ObjectClass() {
            super(Object.class);
        }
    }

    public static class StringClass extends ZeroPackageJavaClass<StringClass> {

        public StringClass() {
            super(String.class);
        }

        @Override
        public boolean hasGenerics(){
            return false;
        }
    }

    public static class UnknownPackageJavaClass extends ZeroPackageJavaClass {

        public UnknownPackageJavaClass(Class<?> class1) {
            super(class1);
        }

        @Override
        public String[] getPackage(){
            return this.class1.getName().substring(2).split(".");
        }
    }

    public static class JavaPrimitiveClass<C extends JavaPrimitiveClass> extends ZeroPackageJavaClass<C> implements CommonClass.PrimitiveClass<C> {

        protected JavaPrimitiveClass(Class<?> class1) {
            super(class1);
        }

        public static class IntegerClass extends JavaPrimitiveClass<IntegerClass> {

            public IntegerClass() {
                super(int.class);
            }

        }

        public static class CharacterClass extends JavaPrimitiveClass<CharacterClass> {

            public CharacterClass() {
                super(char.class);
            }

        }

        public static class LongClass extends JavaPrimitiveClass<LongClass> {

            public LongClass() {
                super(long.class);
            }

        }

        public static class DoubleClass extends JavaPrimitiveClass<DoubleClass> {

            public DoubleClass() {
                super(double.class);
            }

        }

        public static class FloatClass extends JavaPrimitiveClass<FloatClass> {

            public FloatClass() {
                super(float.class);
            }
        }

        public static class BooleanClass extends JavaPrimitiveClass<BooleanClass> {

            public BooleanClass() {
                super(boolean.class);
            }
        }
    }

    protected ZeroPackageJavaClass(Class<?> class1) {
        super(class1);
    }

    @Override
    public String[] getPackage() {
        return new String[]{"java", "lang"};
    }

}
