package org.cyrus.classhandler.java.function.constructor;

import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.classtype.AnnotationClass;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.classtype.GenericList;
import org.cyrus.classhandler.common.function.constructor.Constructor;
import org.cyrus.classhandler.common.line.variable.Parameter;
import org.cyrus.classhandler.java.classtype.AnnotationJavaClass;
import org.cyrus.classhandler.java.classtype.CommonJavaClass;
import org.cyrus.classhandler.java.variable.JParameter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JConstructor<C extends CommonJavaClass> implements Constructor<C> {

    protected C attached;
    protected java.lang.reflect.Constructor<? extends Object> constructor;

    public JConstructor(C attached, java.lang.reflect.Constructor<? extends Object> constructor){
        this.attached = attached;
        this.constructor = constructor;
    }

    @Override
    public C getAttachedClass() {
        return this.attached;
    }

    @Override
    public Visibility getVisibility() {
        if(Modifier.isPrivate(this.constructor.getModifiers())){
            return Visibility.PRIVATE;
        }else if(Modifier.isPublic(this.constructor.getModifiers())){
            return Visibility.PUBLIC;
        }else if(Modifier.isProtected(this.constructor.getModifiers())){
            return Visibility.PROTECTED;
        }
        return Visibility.DEFAULT;
    }

    @Override
    public List<Parameter<? extends CommonClass>> getParameters() {
        java.lang.reflect.Parameter[] params = this.constructor.getParameters();
        List<Parameter<? extends CommonClass>> list = new ArrayList<>();
        for(java.lang.reflect.Parameter param : params){
            list.add(new JParameter(this, param));
        }
        return list;
    }

    @Override
    public String getName() {
        return this.attached.getName();
    }

    @Override
    public Optional<CommonClass<? extends CommonClass>> getReturn() {
        return Optional.of(this.attached);
    }

    @Override
    public boolean hasGenerics(){
        return !getGenerics().isEmpty();
    }

    @Override
    public GenericList getGenerics() {
        Type[] types = this.constructor.getGenericParameterTypes();
        GenericList vc = new GenericList();
        for (Type type : types) {
            if (!(type instanceof ParameterizedType)) {
                continue;
            }
            String name = type.getTypeName();
            if (name.contains(".")) {
                name = "?";
            }
            List<CommonClass.AppliedGenerics> list = new ArrayList<>();
            Type[] typeArguments = ((ParameterizedType) type).getActualTypeArguments();
            for (Type argument : typeArguments) {
                if (!(argument instanceof Class)) {
                    continue;
                }
                CommonJavaClass cjc = CommonJavaClass.of((Class) argument);
                list.add(cjc.toAppliedGenerics(name));
            }
            vc.put(name, list);
        }
        return vc;
    }

    @Override
    public List<AnnotationClass<? extends AnnotationClass>> getAnnotations() {
        List<AnnotationClass<? extends AnnotationClass>> list = new ArrayList<>();
        Annotation[] annotations = this.constructor.getAnnotations();
        if(annotations == null){
            return list;
        }
        for(Annotation annotation : annotations){
            list.add(new AnnotationJavaClass<>(annotation.annotationType()));
        }
        return list;
    }
}
