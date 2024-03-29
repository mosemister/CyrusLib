package org.cyrus.classhandler.writer;

import org.cyrus.classhandler.common.classtype.CommonClass;
import org.cyrus.classhandler.common.classtype.InterfaceClass;
import org.cyrus.classhandler.common.function.Function;
import org.cyrus.classhandler.common.function.method.Method;
import org.cyrus.classhandler.common.function.method.imethod.DefaultMethod;
import org.cyrus.classhandler.common.line.Line;
import org.cyrus.classhandler.common.line.variable.Field;
import org.cyrus.classhandler.common.line.variable.Parameter;
import org.cyrus.classhandler.common.line.variable.Variable;
import org.cyrus.util.ArrayUtils;

import java.util.*;

public class InterfaceClassWriter implements ClassWriter.TypeWriter<InterfaceClass> {

    @Override
    public Class<InterfaceClass> getTargetClass() {
        return InterfaceClass.class;
    }

    @Override
    public List<String> write(CommonClass<? extends CommonClass> class1) {
        int tabIn = 0;
        CommonClass class2 = class1;
        while (class2 != null) {
            if (class2 instanceof CommonClass.Nested) {
                class2 = ((CommonClass.Nested) class2).getAttachedClass();
                tabIn++;
                continue;
            }
            break;
        }
        final int finalTabIn = tabIn;
        List<String> list = new ArrayList<>();
        if(!(class1 instanceof CommonClass.Nested)){
            list.add("package " + ArrayUtils.toString(".", f -> f, class1.getPackage()) + ";");
            List<CommonClass<? extends CommonClass>> imports = class1.getImports();
            imports.stream().forEach(c -> list.add("import " + c.getPackageString() + "." + c.getName() + ";"));
        }
        list.add(writeTabs(tabIn) + writeTopClass(class1));
        list.add("");
        List<? extends Field> fields = class1.getFields();
        if(!fields.isEmpty()) {
            fields.stream().forEach(f -> list.add(writeVariable(f, finalTabIn + 1)));
            list.add("");
        }

        List<String> methods = writeMethods(class1, 1+tabIn);
        if(methods != null){
            list.addAll(methods);
        }
        list.add("}");
        return list;
    }

    @Override
    public Set<TypeWriter<? extends CommonClass>> getTypes() {
        return new HashSet<>(Arrays.asList(this));
    }

    private static String writeVariable(Variable variable, int tab){
        return writeTabs(tab) + variable.getAsJavaLine();
    }

    private static List<String> writeMethods(CommonClass class1, int tab){
        List<? extends Method<? extends CommonClass>> list = class1.getMethods();
        if(list.isEmpty()){
            return null;
        }
        List<String> list1 = new ArrayList<>();
        for(int A = 0; A < list.size(); A++) {
            Method<? extends CommonClass> cons = list.get(A);
            list1.addAll(functionWriter(cons, tab));
            if((A + 1) != list.size()){
                list1.add("");
            }
        }
        return list1;
    }

    private static List<String> functionWriter(Method function, int tab){
        String writer = "";
            if(function.isStatic()){
                writer += (function.getVisibility().getJavaLine()) + "static ";
            }
        if(function.hasGenerics()) {
            String generics = function.getJavaGenericsLine();
            if(generics.length() != 0){
                writer += generics + " ";
            }
        }
            Optional<CommonClass<? extends CommonClass>> opClass = function.getReturn();
            if(opClass.isPresent()){
                writer += opClass.get().getDisplayName() + " ";
            }else{
                writer += "void ";
            }
        writer += function.getName() + "(";
        List<Parameter<? extends CommonClass>> parameters = function.getParameters();
        if(!parameters.isEmpty()){
            writer += ArrayUtils.toString(", ", p -> p.getAsJavaLine(), parameters);
        }
        writer += ")";

        List<String> list = new ArrayList<>();
        if(function.isStatic() || (function instanceof DefaultMethod)){
            writer += " {";
            list.add(writeTabs(tab) + writer);
            List<Line<? extends CommonClass>> lines = ((Function.Writable) function).getLines();
            lines.stream().forEach(l -> list.add(writeTabs(tab + 1) + l.getAsJavaLine()));
            list.add(writeTabs(tab) + "}");
        }else {
            writer += ";";
            list.add(writeTabs(tab) + writer);
        }
        return list;
    }

    private static String writeTopClass(CommonClass class1){
        String writing =  class1.getVisibility().getJavaLine();
        writing += class1.getClassType() + " ";
        writing += class1.getName();
        writing += class1.getJavaAppliedGenericsLine();
        List<InterfaceClass> interfaces = class1.getImplements();
        if(!interfaces.isEmpty()) {
            writing += " extends " + ArrayUtils.toString(",", c -> c.getDisplayName(), interfaces);
        }
        writing += " {";
        return writing;
    }

    private static String writeTabs(int tab){
        String tabs = "";
        for(int A = 0; A < tab; A++){
            tabs += "\t";
        }
        return tabs;
    }
}
