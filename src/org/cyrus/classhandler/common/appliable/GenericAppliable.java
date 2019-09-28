package org.cyrus.classhandler.common.appliable;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.classtype.GenericList;
import org.cyrus.util.ArrayUtils;

import java.util.Collection;
import java.util.stream.Collectors;

public interface GenericAppliable {

    boolean hasGenerics();
    GenericList getGenerics();

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
