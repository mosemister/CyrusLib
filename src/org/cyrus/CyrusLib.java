package org.cyrus;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.custom.classtype.AbstractCommonCustomClass;
import org.cyrus.classhandler.custom.classtype.CustomClassBuilder;
import org.cyrus.classhandler.java.classtype.AbstractCommonJavaClass;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CyrusLib {

    /**
     * A list of all class builders.
     */
    public static final Map<String, Class<? extends CustomClassBuilder<? extends AbstractCommonCustomClass>>> CLASS_BUILDS = new HashMap<>();
    /**
     * All known custom classes
     */
    public static final Set<AbstractCommonCustomClass<? extends AbstractCommonCustomClass>> CUSTOM_CLASSES = new HashSet<>();

    /**
     * gets a class, java or custom.
     * @param clazz The class with package split by '.' without the '.class' on the end
     * @return The class. returns optional if the class is not found.
     */
    public static Optional<? extends CommonClass> getClass(String clazz){
        try {
            Class clazz2 = Class.forName(clazz);
            return Optional.of(AbstractCommonJavaClass.of(clazz2));
        } catch (ClassNotFoundException e) {
            return CUSTOM_CLASSES.stream().filter(c -> clazz.equalsIgnoreCase(c.getPackageString() + "." + c.getName())).findAny();
        }
    }

    /**
     * Registers a custom builder
     * @param classType The class type specified within the class file
     * @param classBuilder The class builder to register
     */
    public static void register(String classType, Class<? extends CustomClassBuilder<? extends AbstractCommonCustomClass>> classBuilder) {
        CLASS_BUILDS.put(classType, classBuilder);
    }

    /**
     * Creates a new instance of a builder by the specified class type. You can find standard class types in 'CommonClass'
     * @param classType the class type of the builder
     * @return the new instance of the specified class builder
     */
    public static Optional<CustomClassBuilder<? extends AbstractCommonCustomClass>> createBuilder(String classType) {
        Class<? extends CustomClassBuilder<? extends AbstractCommonCustomClass>> classBuilderClass = CLASS_BUILDS.get(classType);
        if (classBuilderClass == null) {
            return Optional.empty();
        }
        try {
            return Optional.of(classBuilderClass.getConstructor(new Class[0]).newInstance());
        } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
