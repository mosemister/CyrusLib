package org.cyrus.classhandler.common.classtype;

import org.cyrus.classhandler.common.appliable.AnnotationApplicable;
import org.cyrus.classhandler.common.appliable.GenericApplicable;
import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.function.method.Method;
import org.cyrus.classhandler.common.line.Callable;
import org.cyrus.classhandler.common.line.Caller;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.Writable;
import org.cyrus.classhandler.common.line.callers.StaticClassCall;
import org.cyrus.classhandler.common.line.variable.Field;
import org.cyrus.classhandler.common.line.variable.Variable;
import org.cyrus.classhandler.java.classtype.primitive.ZeroPackageJavaClass;
import org.cyrus.util.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

public interface CommonClass <C extends CommonClass> extends Callable, GenericApplicable, AnnotationApplicable<C> {

    String INTERFACE = "interface";
    String CLASS = "class";
    String ENUM = "enum";
    String ANNOTATION = "@interface";

    /**
     * returns if the class is static
     * @return static class
     */
    boolean isStatic();

    /**
     * Gets the visibility of the class
     * @return The visibility
     */
    Visibility getVisibility();

    /**
     * Gets the package of the class
     * @return The package
     */
    String[] getPackage();

    /**
     * Gets the class type of the class
     * @return Class type
     */
    String getClassType();

    /**
     * Gets the implantation of the class. This is used in the interface class type as 'extends' as well as other class types 'implements'
     * @return Gets the classes this class implements
     */
    List<? extends InterfaceClass> getImplements();

    /**
     * Gets the nested class
     * @return Nested class
     */
    List<? extends CommonClass.Nested> getNestedClasses();

    /**
     * Checks if this class is a instanceof the target class. This is not the same as equals.
     * @param class1 The class to check
     * @return If this class is a instance of the target class
     */
    boolean isInstanceOf(CommonClass class1);

    /**
     * Gets the fields of the class
     * @return the fields of the class
     */
    List<? extends Field> getFields();

    /**
     * Gets the methods of the class. Note this will not include constructors
     * @return the methods of the class
     */
    List<? extends Method<C>> getMethods();

    /**
     * converts this class for use inside a generic
     * @param name The name of the generic
     * @return The constructed applied generic
     */
    AppliedGenerics toAppliedGenerics(String name);

    /**
     * Gets all callable objects within the class
     * @return A set of all callable objects within the class
     */
    default Set<Callable> getCalls(){
        Set<Callable> set = new HashSet<>();
        set.addAll(this.getFields());
        set.addAll(this.getMethods());
        return set;
    }

    /**
     * Gets a specific type of callable within the class
     * @param class1 Class of the callable
     * @param <C> Class of the callable
     * @return All callables within the class of the specified type
     */
    default <C extends Callable> Set<C> getCalls(Class<C> class1){
        return (Set<C>) getCalls().stream().filter(c -> class1.isInstance(c)).collect(Collectors.toSet());
    }

    /**
     * Checks if the target class is the same class as this class. This is not the same as equals
     * @param class1 The class to check against
     * @return if they match
     */
    default boolean isMatch(CommonClass<? extends CommonClass> class1){
        if(!class1.getName().equals(getName())){
            return false;
        }
        if(!class1.getPackageString().equals(getPackageString())){
            return false;
        }
        return true;
    }

    /**
     * Get the class name as it should show as variable
     * @return the name of the class with generics applied
     */
    default String getDisplayName(){
        return getName() + getJavaGenericsLine();
    }

    /**
     * Gets the package as it shows in java
     * @return gets the package as shown in java
     */
    default String getPackageString(){
        return ArrayUtils.toString(".", t -> t, getPackage());
    }

    /**
     * Gets the imports that the class has
     * @return gets the imports that the class has
     */
    default List<CommonClass<? extends CommonClass>> getImports(){
        List<CommonClass<? extends CommonClass>> imports = new ArrayList<>();
        getImplements().stream().forEach(i -> imports.add(i));
        getFields().stream().forEach(f -> {
            if(isValidImport(this, f.getReturn().get())){
                imports.add(f.getReturn().get());
            }
            if(f instanceof Variable.Attachable){
                Optional<Line> opLine = ((Variable.Attachable) f).getAttachedLine();
                opLine.ifPresent(l -> l.getImports().stream().forEach(i -> imports.add((CommonClass<? extends CommonClass>) i)));
            }
        });
        getMethods().stream().forEach(m -> {
            m.getReturn().ifPresent(r -> imports.add(r));
            m.getParameters().stream().forEach(i -> imports.add(i.getReturn().get()));
            if(m instanceof Method.Writable){
                List<Line<? extends CommonClass>> list = ((Method.Writable<CommonClass>) m).getLines();
                list.stream().forEach(l -> imports.addAll(l.getImports()));
            }
        });
        List<CommonClass<? extends CommonClass>> importsWoDup = new ArrayList<>();
        imports.stream().filter(c -> isValidImport(this, convertImport(c))).forEach(cNone -> {
            CommonClass c = convertImport(cNone);
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

    /**
     * This is needed as a class is callable
     * @return A optional of itself
     */
    @Override
    default Optional<CommonClass<? extends CommonClass>> getReturn(){
        return Optional.of(this);
    }

    /**
     * Creates a instance of a static class caller for this class
     * @return A static caller
     */
    @Override
    default Caller createCaller(CommonClass<? extends CommonClass> clazz){
        return new StaticClassCall(clazz, this);
    }


    /**
     * The generics for a class
     * @param <C> itself
     */
    interface AppliedGenerics<C extends AppliedGenerics> extends CommonClass<C> {

        String getOriginalDisplayName();

    }

    /**
     * Base interface for a nested class
     * @param <C> itself
     */
    interface Nested<C extends Nested> extends CommonClass<C> {

        CommonClass getAttachedClass();

    }

    /**
     * Base class for primitive classes
     * @param <C> itself
     */
    interface PrimitiveClass<C extends PrimitiveClass> extends CommonClass<C>{

    }

    /**
     * Base class for writable classes
     * @param <C> itself
     */
    interface Writable<C extends CommonClass> extends CommonClass<C>, org.cyrus.classhandler.common.line.Writable {

        /**
         * Sets if the class is static
         * @param tatic if the class should be static
         * @return itself for chaining
         */
        Writable setStatic(boolean tatic);

        /**
         * Sets the classes package
         * @param ackage the package to be
         * @return itself for chaining
         */
        Writable setPackage(String... ackage);

        /**
         * Sets the name of the class
         * @param name the new name
         * @return itself for chaining
         */
        Writable setName(String name);

        /**
         * sets the visibility of the class
         * @param visibility the new visibility
         * @return itself for chaining
         */
        Writable setVisibility(Visibility visibility);

        @Override
        default Optional<String> getDescriptionOfError() {
            Optional<? extends Field> opField = getFields().stream().filter(f -> f.getDescriptionOfError().isPresent()).findAny();
            if(opField.isPresent()){
                return opField.get().getDescriptionOfError();
            }
            Optional<? extends Method<C>> opMethod = getMethods().stream().filter(m -> m instanceof Method.Writable).filter(m -> ((Method.Writable) m).getDescriptionOfError().isPresent()).findAny();
            if(opMethod.isPresent()){
                return ((Method.Writable)opMethod.get()).getDescriptionOfError();
            }
            return Optional.empty();
        }
    }

    static CommonClass<? extends CommonClass> convertImport(CommonClass<? extends CommonClass> test){
        if(test instanceof ArrayClass){
            return ((ArrayClass) test).getNoneArrayClass();
        }
        return test;
    }

    /**
     * Checks if the class can import the other
     * @param importingTo the class being imported to
     * @param import1 the class being imported
     * @return is possible
     */
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
