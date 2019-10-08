package org.cyrus.classhandler.custom;

import org.cyrus.CyrusLib;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.custom.classtype.AbstractCommonCustomClass;
import org.cyrus.classhandler.custom.classtype.CustomClassBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface JavaClassReader {

    String[] getPackage() throws IOException;
    Set<String> getImports();
    String getClassType() throws IOException;
    String getName() throws IOException;
    Optional<String> getExtends();
    Set<String> getImplements();
    List<List<String>> getConstructors();
    List<List<String>> getMethods();

    default AbstractCommonCustomClass<? extends AbstractCommonCustomClass> createBaseClass() throws IOException{
        Optional<CustomClassBuilder<? extends AbstractCommonCustomClass>> opBuilder = CyrusLib.createBuilder(this.getClassType());
        if(!opBuilder.isPresent()){
            throw new IOException("Unknown ClassType of " + this.getClassType() + " - Was this registered?");
        }
        CustomClassBuilder<? extends AbstractCommonCustomClass> builder = opBuilder.get();
        builder.setPackage(getPackage());
        builder.setName(getName());
        return builder.build();
    }

    default List<CommonClass<? extends CommonClass>> getImportedClasses(boolean ignoreErrors) throws IOException{
        List<CommonClass<? extends CommonClass>> classes = new ArrayList<>();
        /*for(String import1 : getImports()){
            Optional<CommonClass<? extends CommonClass>> opClass = CyrusLib.getClass(import1);
            if(!opClass.isPresent()){
                if(ignoreErrors){
                    continue;
                }
                throw new IOException("Could not load class " + import1);
            }
            classes.add(opClass.get());
        }*/
        return classes;
    }





}
