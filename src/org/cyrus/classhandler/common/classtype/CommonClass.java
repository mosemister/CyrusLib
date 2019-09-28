package org.cyrus.classhandler.common.classtype;

import org.cyrus.classhandler.common.appliable.AnnotationAppliable;
import org.cyrus.classhandler.common.appliable.GenericAppliable;
import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.function.method.Method;
import org.cyrus.classhandler.common.line.Callable;
import org.cyrus.classhandler.common.line.Caller;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.callers.StaticClassCall;
import org.cyrus.classhandler.common.line.variable.Field;
import org.cyrus.classhandler.common.line.variable.Store;
import org.cyrus.classhandler.java.classtype.primitive.ZeroPackageJavaClass;
import org.cyrus.util.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

public interface CommonClass <C extends CommonClass> extends Callable, GenericAppliable, AnnotationAppliable<C> {

    String INTERFACE = "interface";
    String CLASS = "class";
    String ENUM = "enum";
    String ANNOTATION = "@interface";

    boolean isStatic();
    Visibility getVisibility();
    String[] getPackage();
    String getClassType();
    List<? extends InterfaceClass> getImplements();
    List<? extends CommonClass.Nested> getNestedClasses();
    boolean isInstanceOf(CommonClass class1);
    List<? extends Field> getFields();
    List<? extends Method<C>> getMethods();
    AppliedGenerics toAppliedGenerics(String name);

    default Set<Callable> getCalls(){
        Set<Callable> set = new HashSet<>();
        set.addAll(this.getFields());
        set.addAll(this.getMethods());
        return set;
    }

    default <C extends Callable> Set<C> getCalls(Class<C> class1){
        return (Set<C>) getCalls().stream().filter(c -> class1.isInstance(c)).collect(Collectors.toSet());
    }

    default boolean isMatch(CommonClass<? extends CommonClass> class1){
        if(!class1.getName().equals(getName())){
            return false;
        }
        if(!class1.getPackageString().equals(getPackageString())){
            return false;
        }
        return true;
    }

    default String getDisplayName(){
        return getName() + getJavaGenericsLine();
    }

    default String getPackageString(){
        return ArrayUtils.toString(".", t -> t, getPackage());
    }

    default List<CommonClass<? extends CommonClass>> getImports(){
        List<CommonClass<? extends CommonClass>> imports = new ArrayList<>();
        getImplements().stream().forEach(i -> imports.add(i));
        getFields().stream().forEach(f -> {
            if(isValidImport(this, f.getReturn().get())){
                imports.add(f.getReturn().get());
            }
            if(f instanceof Store.Attachable){
                Optional<Line> opLine = ((Store.Attachable) f).getAttachedLine();
                opLine.ifPresent(l -> l.getImports().stream().forEach(i -> imports.add(i)));
            }
        });
        getMethods().stream().forEach(m -> {
            System.out.println("Method: " + m.getName());
            System.out.println("Return: " + m.getReturn());
            m.getReturn().ifPresent(r -> imports.add(r));
            m.getParameters().stream().forEach(i -> imports.add(i.getReturn().get()));
            if(m instanceof Method.Writable){
                List<Line> list = ((Method.Writable<CommonClass>) m).getLines();
                list.stream().forEach(l -> imports.addAll(l.getImports()));
            }
        });
        List<CommonClass<? extends CommonClass>> importsWoDup = new ArrayList<>();
        imports.stream().filter(c -> isValidImport(this, c)).forEach(c -> {
            if(!importsWoDup.stream().anyMatch(c2 -> {
                if(c2.getPackageString().equals(c.getPackageString()) && c2.getName().equals(c.getName())){
                    return true;
                }
                return false;
            })){
                importsWoDup.add(c);
            }
        });
        return importsWoDup;
    }

    @Override
    default Optional<CommonClass<? extends CommonClass>> getReturn(){
        return Optional.of(this);
    }

    @Override
    default Caller getCaller(){
        return new StaticClassCall(this);
    }

    default String toCommonString(){
        return getPackageString() + "." + getName();
    }

    interface AppliedGenerics<C extends AppliedGenerics> extends CommonClass<C> {

        String getOriginalDisplayName();

    }

    interface Nested<C extends Nested> extends CommonClass<C> {

        CommonClass getAttachedClass();

    }

    interface PrimitiveClass<C extends PrimitiveClass> extends CommonClass<C>{

    }

    interface Writable<C extends CommonClass> extends CommonClass<C> {

        Writable setStatic(boolean tatic);
        Writable setPackage(String... ackage);
        Writable setName(String name);
        Writable setVisibility(Visibility visibility);

    }

    static boolean isValidImport(CommonClass importingTo, CommonClass import1){
        if(import1 instanceof ZeroPackageJavaClass){
            return false;
        }
        if(import1.getPackageString().equals(importingTo.getPackageString())){
            return false;
        }
        return true;
    }
}
