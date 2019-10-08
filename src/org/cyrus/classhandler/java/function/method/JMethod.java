package org.cyrus.classhandler.java.function.method;

import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.classtype.AnnotationClass;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.classtype.GenericList;
import org.cyrus.classhandler.common.function.method.Method;
import org.cyrus.classhandler.common.function.method.imethod.InterfaceMethod;
import org.cyrus.classhandler.common.line.variable.Parameter;
import org.cyrus.classhandler.java.classtype.AbstractCommonJavaClass;
import org.cyrus.classhandler.java.classtype.InterfaceJavaClass;
import org.cyrus.classhandler.java.variable.JParameter;

import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JMethod<C extends AbstractCommonJavaClass> implements Method<C> {

    public static class JavaInterfaceMethod <C extends InterfaceJavaClass> extends JMethod<C> implements InterfaceMethod<C> {

        public JavaInterfaceMethod(C class1, java.lang.reflect.Method method) {
            super(class1, method);
        }
    }

    protected C target;
    protected java.lang.reflect.Method method;

    public JMethod(C class1, java.lang.reflect.Method method){
        this.method = method;
        this.target = class1;
    }

    @Override
    public boolean hasGenerics(){
        return !getGenerics().isEmpty();
    }

    @Override
    public List<AnnotationClass<? extends AnnotationClass>> getAnnotations() {
        return null;
    }

    @Override
    public GenericList getGenerics(){
        Type[] types = this.method.getGenericParameterTypes();
        GenericList vc = new GenericList();
        for(Type type : types){
            if(!(type instanceof ParameterizedType)){
                continue;
            }
            String name = type.getTypeName();
            if(name.contains(".")) {
                name = "?";
            }
            List<CommonClass.AppliedGenerics> list = new ArrayList<>();
            Type[] typeArguments = ((ParameterizedType) type).getActualTypeArguments();
            for(Type argument : typeArguments){
                if(!(argument instanceof Class)){
                    continue;
                }
                AbstractCommonJavaClass cjc = AbstractCommonJavaClass.of((Class)argument);
                list.add(cjc.toAppliedGenerics(name));
            }
            vc.put(name, list);
        }
        return vc;
    }

    @Override
    public Visibility getVisibility() {
        if(Modifier.isPublic(this.method.getModifiers())){
            return Visibility.PUBLIC;
        }
        if(Modifier.isPrivate(this.method.getModifiers())){
            return Visibility.PRIVATE;
        }
        if(Modifier.isProtected(this.method.getModifiers())){
            return Visibility.PROTECTED;
        }
        return Visibility.DEFAULT;
    }

    @Override
    public List<Parameter<? extends CommonClass>> getParameters() {
        java.lang.reflect.Parameter[] params = this.method.getParameters();
        List<Parameter<? extends CommonClass>> list = new ArrayList<>();
        for(java.lang.reflect.Parameter param : params){
            list.add(new JParameter(this, param));
        }
        return list;
    }

    @Override
    public boolean isStatic() {
        return Modifier.isStatic(this.method.getModifiers());
    }

    @Override
    public String getName() {
        return this.method.getName();
    }

    @Override
    public Optional<CommonClass<? extends CommonClass>> getReturn() {
        Class<?> class1 = this.method.getReturnType();
        if(class1.getName().equals("void")){
            return Optional.empty();
        }
        return Optional.of(AbstractCommonJavaClass.of(class1));
    }

    @Override
    public C getAttachedClass() {
        return this.target;
    }
}
