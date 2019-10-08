package org.cyrus.classhandler.custom.reader;

import org.cyrus.CyrusLib;
import org.cyrus.classhandler.common.Visibility;
import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.classtype.StandardClass;
import org.cyrus.classhandler.common.function.constructor.Constructor;
import org.cyrus.classhandler.common.function.method.Method;
import org.cyrus.classhandler.common.line.variable.Field;
import org.cyrus.classhandler.custom.classtype.AbstractCommonCustomClass;
import org.cyrus.classhandler.custom.classtype.StandardCustomClass;

import java.util.*;
import java.util.stream.Stream;

public class StandardClassReader implements ClassReader<StandardCustomClass> {

    protected String[] package1;
    protected String[] lines;

    public StandardClassReader(String... lines){
        this.lines = lines;
    }

    @Override
    public String[] readPackage() {
        String packageLine = null;
        for(String line : this.lines){
            if(line.startsWith("package")){
                packageLine = line;
                break;
            }
        }
        this.package1 = packageLine.substring(7, packageLine.length() - 1).split(".");
        return this.package1;
    }

    @Override
    public Set<CommonClass> readImports() {
        Set<CommonClass> set = new HashSet<>();
        for(String line : this.lines){
            if(line.startsWith("import")){
                CyrusLib.getClass(line.substring(7, line.length() - 1)).ifPresent(c -> set.add(c));
            }
        }
        return set;
    }

    @Override
    public StandardCustomClass<StandardCustomClass> readClassInit() {
        String classLine = null;
        for(String line : this.lines){
            if(line.contains("class")){
                classLine = line;
                break;
            }
        }
        String[] sections = classLine.split(" ");
        Visibility visibility = Visibility.PUBLIC;
        boolean isAbstract = false;
        boolean isStatic = false;
        String name = null;
        for(String section : sections){
            Optional<Visibility> opVis = Stream.of(Visibility.values()).filter(v -> section.equals(v.getJavaLine())).findAny();
            if(opVis.isPresent()){
                visibility = opVis.get();
                continue;
            }
            if(section.equals("abstract")){
                isAbstract = true;
            }
            if(section.equals("static")){
                isStatic = true;
            }
            if(name == null && Character.isUpperCase(section.charAt(0))){
                name = section;
            }
        }
        StandardCustomClass class1 = (StandardCustomClass) new StandardCustomClass.StandardClassBuilder().setPackage(this.package1).setName(name).setVisibility(visibility).build();
        class1.setAbstract(isAbstract);
        class1.setStatic(isStatic);
        return class1;
    }

    @Override
    public Optional<StandardClass> readExtends() {
        String classLine = null;
        for(String line : this.lines){
            if(line.contains("class")){
                classLine = line;
                break;
            }
        }
        String[] sections = classLine.split(" ");
        for(int A = 0; A < sections.length; A++){
            String section = sections[A];
            if(section.equals("extends")){
                String classString = sections[A+1];
                if(classString.contains(".")){
                    Optional<? extends CommonClass> opClass = CyrusLib.getClass(classString);
                    if(opClass.isPresent()){
                        return Optional.of((StandardClass) opClass.get());
                    }else{
                        return Optional.empty();
                    }
                }else{
                    Optional<CommonClass> opClass = readImports().stream().filter(c -> c.getName().equals(classString)).findAny();
                    if(opClass.isPresent()){
                        return Optional.of((StandardClass) opClass.get());
                    }else{
                        return Optional.empty();
                    }
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Set<CommonClass> readImplements() {
        return new HashSet<>();
    }

    @Override
    public Set<Field> readFields() {
        return new HashSet<>();
    }

    @Override
    public Set<Constructor<StandardCustomClass>> readConstructors(AbstractCommonCustomClass clazz) {
        List<String> lines = new ArrayList<>();
        boolean writing = false;
        int count = 0;
        for(String line : this.lines){
            if(line.contains(clazz.getName()) && line.contains("(")){
                writing = true;
                count = getWhitespace(line);
            }
            if(writing){
                lines.add(line);
                if(line.contains("}") && getWhitespace(line) == count){
                    writing = false;
                    break;
                }
            }
        }

        String initLine = lines.get(0);
        String beforePara = "";
        List<String> para = new ArrayList<>();
        boolean start = false;
        String current = "";
        for(int A = 0; A < initLine.length(); A++){
            char cha = initLine.charAt(A);
            if(!start){
                if(cha == '('){
                    start = true;
                    continue;
                }
                beforePara = beforePara + cha;
            }
            if (start) {
                if(cha == ')'){
                    break;
                }
                if(cha == ','){
                    para.add(current);
                    current = "";
                    continue;
                }
                current = current + cha;
            }
        }

        return new HashSet<>();
    }

    @Override
    public Set<Method<StandardCustomClass>> readMethods(AbstractCommonCustomClass clazz) {
        return new HashSet<>();
    }

    @Override
    public String getClassType() {
        return CommonClass.CLASS;
    }

    @Override
    public ClassReader<StandardCustomClass> newInstance(String... lines) {
        return new StandardClassReader(lines);
    }

    private int getWhitespace(String line){
        for(int A = 0; A < line.replaceAll("\t", "    ").length(); A++){
            char cha = line.charAt(A);
            if(cha != ' '){
                return (A - 1);
            }
        }
        return 0;
    }

}
