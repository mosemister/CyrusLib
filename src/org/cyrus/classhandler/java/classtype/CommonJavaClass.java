package org.cyrus.classhandler.java.classtype;

import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.classtype.AnnotationClass;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.classtype.GenericList;
import org.cyrus.classhandler.common.classtype.InterfaceClass;
import org.cyrus.classhandler.common.function.method.Method;
import org.cyrus.classhandler.common.line.variable.Field;
import org.cyrus.classhandler.java.classtype.primitive.ZeroPackageJavaClass;
import org.cyrus.classhandler.java.function.method.JMethod;
import org.cyrus.classhandler.java.variable.JField;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public abstract class CommonJavaClass<C extends CommonJavaClass> implements CommonClass<C> {

    protected Class<? extends Object> class1;

    protected CommonJavaClass(Class<? extends Object> class1){
        this.class1 = class1;
    }

    public Class<? extends Object> getTargetClass(){
        return this.class1;
    }

    public boolean equalsExact(Class<? extends Object> class1){
        String name = class1.getName();
        if(class1.isArray()){
            name = name.substring(0, name.length() - 2);
        }
        if(this.class1.getName().equals(name)){
            return true;
        }
        return false;
    }

    @Override
    public boolean hasGenerics(){
        return !getGenerics().isEmpty();
    }

    @Override
    public GenericList getGenerics(){
        Type[] types = this.class1.getGenericInterfaces();
        GenericList vc = new GenericList();
        for(Type type : types){
            if(!(type instanceof ParameterizedType)){
                continue;
            }
            String name = type.getTypeName();
            if(name.contains(".")) {
                String genName = this.class1.toGenericString();
                String[] sections = genName.substring(0, genName.length() - 1).split("<");
                if(sections.length == 0){
                    name = "?";
                }else {
                    name = sections[sections.length - 1];
                }
            }
            List<CommonClass.AppliedGenerics> list = new ArrayList<>();
            Type[] typeArguments = ((ParameterizedType) type).getActualTypeArguments();
            for(Type argument : typeArguments){
                if(!(argument instanceof Class)){
                    continue;
                }
                CommonJavaClass cjc = CommonJavaClass.of((Class)argument);
                list.add(cjc.toAppliedGenerics(name));
            }
            vc.put(name, list);
        }
        return vc;
    }

    @Override
    public boolean isStatic() {
        return Modifier.isStatic(this.class1.getModifiers());
    }

    @Override
    public String[] getPackage() {
        Package package1 = this.class1.getPackage();
        if(package1 == null){
            System.err.println("Java package is null: " + getName());
            return null;
        }
        return package1.getName().split(Pattern.quote("."));
    }

    @Override
    public List<InterfaceClass> getImplements() {
        Class<? extends Object>[] interfaces = this.class1.getInterfaces();
        List<InterfaceClass> list = new ArrayList<>();
        for(Class<? extends Object> class1 : interfaces){
            list.add((InterfaceClass) org.cyrus.classhandler.java.classtype.CommonJavaClass.of(class1));
        }
        return list;
    }

    @Override
    public List<CommonClass.Nested> getNestedClasses() {
        Class<? extends Object>[] classes = this.class1.getClasses();
        List<CommonClass.Nested> list = new ArrayList<>();
        for(Class<? extends Object> class1 : classes){
            list.add((Nested) CommonJavaClass.of(class1));
        }
        return list;
    }

    @Override
    public boolean isInstanceOf(CommonClass class1) {
        if(!(class1 instanceof CommonJavaClass)){
            return false;
        }
        CommonJavaClass cjc = (CommonJavaClass) class1;
        System.out.println("Comparing: " + this.class1.getSimpleName() + " | " + cjc.class1.getSimpleName());
        return cjc.class1.isAssignableFrom(this.class1);
    }

    @Override
    public List<Field> getFields() {
        java.lang.reflect.Field[] fields = this.class1.getFields();
        List<Field> list = new ArrayList<>();
        for(java.lang.reflect.Field field : fields){
            list.add(new JField(this, field));
        }
        return list;
    }

    @Override
    public List<Method<C>> getMethods() {
        java.lang.reflect.Method[] methods = this.class1.getDeclaredMethods();
        List<Method<C>> list = new ArrayList<>();
        for(java.lang.reflect.Method method : methods){
            list.add(new JMethod(this, method));
        }
        return list;
    }

    @Override
    public Visibility getVisibility() {
        int modifier = this.class1.getModifiers();
        if(Modifier.isPrivate(modifier)){
            return Visibility.PRIVATE;
        }else if(Modifier.isProtected(modifier)){
            return Visibility.PROTECTED;
        }else if(Modifier.isPublic(modifier)){
            return Visibility.PUBLIC;
        }
        return Visibility.DEFAULT;
    }

    @Override
    public List<AnnotationClass<? extends AnnotationClass>> getAnnotations(){
        List<AnnotationClass<? extends AnnotationClass>> list = new ArrayList<>();
        Annotation[] annotations = this.class1.getAnnotations();
        if(annotations == null){
            return list;
        }
        for(Annotation annotation : annotations){
            list.add(new AnnotationJavaClass<>(annotation.annotationType()));
        }
        return list;
    }

    @Override
    public String getName() {
        return this.class1.getSimpleName();
    }

    public static CommonJavaClass of(Class<? extends Object> class1){
        Class<? extends Object> class2 = class1;
        if(class1.isArray()){
            Package package1 = class1.getPackage();
            if(package1 == null){
                try {
                    class2 = Class.forName("java.lang." + class1.getSimpleName().substring(0, class1.getSimpleName().length() - 2));
                } catch (ClassNotFoundException e) {
                    for(ZeroPackageJavaClass.JavaPrimitiveClass jc : ZeroPackageJavaClass.PRIMITIVE){
                        if (class1.getSimpleName().startsWith(jc.getName())){
                            return jc;
                        }
                    }
                    if(class1.getName().contains(".") && class1.getName().startsWith("[")){
                        return new ZeroPackageJavaClass.UnknownPackageJavaClass(class1);
                    }
                    System.err.println("Could not find: " + class1.getName());
                    e.printStackTrace();
                }
            }else {
                String name = package1.getName() + "." + class1.getSimpleName();
                try {
                    class2 = Class.forName(name.substring(0, name.length() - 2));
                } catch (ClassNotFoundException e) {
                    System.out.println("V2: Could not find Non-Array version of " + class1.getName());
                    e.printStackTrace();
                }
            }
        }
        for(ZeroPackageJavaClass jc : ZeroPackageJavaClass.PRIMITIVE){
            if (class2.isAssignableFrom(jc.class1)){
                return jc;
            }
        }
        for(ZeroPackageJavaClass jc : ZeroPackageJavaClass.ZERO_PACKAGE){
            if (jc.equalsExact(class2)){
                return jc;
            }
        }
        if (class2.isPrimitive()){
            System.err.println("Unknown Primitive type: " + class2.getName() + " in CommandJavaClass.of");
            return null;
        }
        if(class2.isAnnotation()){
            return new AnnotationJavaClass(class2);
        }
        if(class2.isInterface()){
            return new InterfaceJavaClass(class2);
        }
        if(class2.isEnum()){
            return new EnumJavaClass(class2);
        }
        return new StandardJavaClass(class2);
    }

}
