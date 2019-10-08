package org.cyrus.classhandler.custom.reader;

import org.cyrus.classhandler.custom.classtype.AbstractCommonCustomClass;
import org.cyrus.classhandler.custom.classtype.StandardCustomClass;

import java.util.*;

public class CustomClassReader {

    protected Set<String> classes = new HashSet<>();

    public static final Set<ClassReader<? extends AbstractCommonCustomClass>> CLASS_READERS = new HashSet<>(Arrays.asList(new StandardClassReader()));

    public CustomClassReader register(String... clazz){
        this.classes.addAll(Arrays.asList(clazz));
        return this;
    }

    public Set<AbstractCommonCustomClass> build(){
        Map<AbstractCommonCustomClass, ClassReader<? extends AbstractCommonCustomClass>> map = new HashMap<>();
        this.classes.stream().forEach(cLines -> {
            String[] lines = cLines.split("\n");
            String classInitLine = null;
            int A = 0;
            for(String line : lines){
                String[] sections = line.split(" ");
                if(line.endsWith("{")){
                    classInitLine = line;
                    for(; A < sections.length; A++){
                        if(Character.isUpperCase(sections[A].charAt(0))){
                            break;
                        }
                    }
                    break;
                }
            }
            String classType = classInitLine.split(" ")[A-1];
            ClassReader<? extends AbstractCommonCustomClass> reader = CLASS_READERS.stream().filter(c -> c.getClassType().equals(classType)).findAny().get().newInstance(lines);
            reader.readPackage();
            AbstractCommonCustomClass clazz = reader.readClassInit();
            map.put(clazz, reader);
        });
        map.entrySet().stream().forEach(e -> e.getValue().readConstructors(e.getKey()));
        map.entrySet().stream().forEach(e -> e.getValue().readExtends().ifPresent(ex -> {
            if (e.getKey() instanceof StandardCustomClass){
                StandardCustomClass scc = (StandardCustomClass) e.getKey();
                scc.setExtends(ex);
            }
        }));
        map.entrySet().stream().forEach(e -> e.getKey().getImplements().addAll(e.getValue().readImplements()));

        map.entrySet().stream().forEach(e -> e.getValue().readMethods(e.getKey()));

        return map.keySet();
    }
}
