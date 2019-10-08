package org.cyrus.classhandler.common.appliable;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.classtype.GenericList;
import org.cyrus.util.ArrayUtils;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * An interfface desgined to extend onto anything that
 * Generics (example - <A extends Object>) can apply to
 */
public interface GenericApplicable {

    /**
     * Returns if generics can be found
     * @return true if generic has been found, false if not
     */
    boolean hasGenerics();

    /**
     * Gets a list of all generics applied
     * @return The generic list applied
     */
    GenericList getGenerics();

    /**
     * Gets the default generics as written in Java
     * @return A Java parsed line of generics
     */
    default String getJavaGenericsLine(){
        if(!hasGenerics()){
            return "";
        }
        String generics = ArrayUtils.toString(",", v -> v, getGenerics().keySet().stream().filter(c -> !c.equals("?")).collect(Collectors.toSet()));
        if(generics == null){
            return "";
        }
        return "<" + generics + ">";
    }

    /**
     * Gets the applied generics as written in Java
     * @return A Java parsed line of generics
     */
    default String getJavaAppliedGenericsLine(){
        if(!hasGenerics()){
            return "";
        }
        return "<" + ArrayUtils.toString(",", v -> {
            Collection<CommonClass.AppliedGenerics> collection = v.getValue();
            if(collection.isEmpty()){
                return v.getKey() + " extends Object";
            }
            return v.getKey() + " extends " + ArrayUtils.toString(" && ", c -> c.getName(), v.getValue());
        }, getGenerics().entrySet()) + ">";
    }
}
