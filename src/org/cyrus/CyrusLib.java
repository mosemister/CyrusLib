package org.cyrus;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.custom.classtype.CommonCustomClass;
import org.cyrus.classhandler.custom.classtype.CustomClassBuilder;
import org.cyrus.classhandler.java.classtype.CommonJavaClass;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CyrusLib {

    public static final Map<String, Class<? extends CustomClassBuilder<? extends CommonCustomClass>>> CLASS_BUILDS = new HashMap<>();
    public static final Set<CommonCustomClass<? extends CommonCustomClass>> CUSTOM_CLASSES = new HashSet<>();
    public static final Set<CommonJavaClass<? extends CommonJavaClass>> LOADED_IN_CLASSES = new HashSet<>();

    public static Optional<? extends CommonClass> getClass(String clazz){
        try {
            Class clazz2 = Class.forName(clazz);
            return Optional.of(CommonJavaClass.of(clazz2));
        } catch (ClassNotFoundException e) {
            return CUSTOM_CLASSES.stream().filter(c -> clazz.equalsIgnoreCase(c.getPackageString() + "." + c.getName())).findAny();
        }
    }

    public static void register(String classType, Class<? extends CustomClassBuilder<? extends CommonCustomClass>> classBuilder) {
        CLASS_BUILDS.put(classType, classBuilder);
    }

    public static Optional<CustomClassBuilder<? extends CommonCustomClass>> createBuilder(String classType) {
        Class<? extends CustomClassBuilder<? extends CommonCustomClass>> classBuilderClass = CLASS_BUILDS.get(classType);
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

    public static <T extends CommonCustomClass> Optional<T> createBuilder(Class<T> class1) {
        for (String key : CLASS_BUILDS.keySet()) {
            Optional<CustomClassBuilder<? extends CommonCustomClass>> builder = createBuilder(key);
            if (builder.get().getTargetClass().isAssignableFrom(class1)) {
                return Optional.of((T) builder.get());
            }
        }
        return Optional.empty();
    }
}
