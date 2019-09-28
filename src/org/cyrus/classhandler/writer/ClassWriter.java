package org.cyrus.classhandler.writer;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.classtype.EnumClass;
import org.cyrus.classhandler.common.classtype.InterfaceClass;
import org.cyrus.classhandler.common.classtype.StandardClass;

import java.util.*;

public interface ClassWriter {

    List<String> write(CommonClass<? extends CommonClass> commonClass);
    Set<TypeWriter<? extends CommonClass>> getTypes();

    default <T extends CommonClass> ClassWriter.TypeWriter<T> getWriter(Class<T> class1){
        return (TypeWriter<T>) getTypes().stream().filter(t -> t.getTargetClass().isAssignableFrom(class1)).findAny().get();
    }

    default ClassWriter.TypeWriter<InterfaceClass> getInterfaceWriter(){
        return getWriter(InterfaceClass.class);
    }

    default ClassWriter.TypeWriter<StandardClass> getStandardWriter(){
        return getWriter(StandardClass.class);
    }

    default ClassWriter.TypeWriter<EnumClass> getEnumWriter(){
        return getWriter(EnumClass.class);
    }

    interface TypeWriter <T extends CommonClass>  extends ClassWriter {

        Class<T> getTargetClass();
        List<String> write(CommonClass<? extends CommonClass> cClass);
    }

    class AbstractClassWriter implements ClassWriter{

        Set<ClassWriter.TypeWriter<? extends CommonClass>> types = new HashSet<>();

        public AbstractClassWriter(){
            this.types.add(new StandardClassWriter());
            this.types.add(new InterfaceClassWriter());
        }

        @Override
        public List<String> write(CommonClass<? extends CommonClass> commonClass) {
            Optional<TypeWriter<? extends CommonClass>> opWriter = types.stream().filter(t -> t.getTargetClass().isInstance(commonClass)).findAny();
            if(!opWriter.isPresent()){
                System.err.println("Could not find a ClassWriter for " + commonClass.getClassType());
                return new ArrayList<>();
            }
            return opWriter.get().write(commonClass);
        }

        @Override
        public Set<TypeWriter<? extends CommonClass>> getTypes() {
            return this.types;
        }

    }
}
