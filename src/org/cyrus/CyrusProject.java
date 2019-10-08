package org.cyrus;

import org.cyrus.classhandler.common.classtype.CommonClass;

import java.util.HashSet;
import java.util.Set;

public class CyrusProject {

    Set<CommonClass<? extends CommonClass>> classes = new HashSet<>();

    public Set<CommonClass<? extends CommonClass>> getClasses(){
        return this.classes;
    }
}
