package org.cyrus.project;

import org.cyrus.classhandler.common.classtype.ArrayClass;
import org.cyrus.classhandler.common.function.method.Method;
import org.cyrus.classhandler.custom.classtype.CommonCustomClass;
import org.cyrus.classhandler.java.classtype.primitive.ZeroPackageJavaClass;
import org.cyrus.classhandler.writer.ClassWriter;
import org.cyrus.util.ArrayUtils;

import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class CustomProject {

    protected List<CommonCustomClass<? extends CommonCustomClass>> classes = new ArrayList<>();

    public List<CommonCustomClass<? extends CommonCustomClass>> getClasses(){
        return this.classes;
    }

    public Set<File> save(File folder) throws IOException{
        Set<File> files = new HashSet<>();
        getClasses().stream().forEach(c -> {
            File file = new File(folder, "java/" + ArrayUtils.toString("/", t -> t, c.getPackage()) + "/" + c.getName() + ".java");
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                FileWriter writer = new FileWriter(file);
                List<String> lines = new ClassWriter.AbstractClassWriter().write(c);
                for(String line : lines){
                    writer.write(line + "\n");
                }
                writer.flush();
                writer.close();
                files.add(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return files;
    }

    public File build(File folder, String fileName) throws IOException {
        Set<File> javaFiles = save(folder);
        //build system
        Optional<CommonCustomClass<? extends CommonCustomClass>> opMainClass = this.classes.stream().filter(c -> c.getMethods().stream()
                .filter(m -> m.getName().equals("main"))
                .filter(m -> m.isStatic())
                .filter(m -> !m.getReturn().isPresent())
                .filter(m -> m.getParameters().size() == 1)
                .filter(m -> m.getParameters().get(0).getReturn().isPresent())
                .filter(m -> m.getParameters().get(0).getReturn().get() instanceof ArrayClass)
                .anyMatch(m -> ((ArrayClass)m.getParameters().get(0).getReturn().get()).getNoneArrayClass().isMatch(ZeroPackageJavaClass.STRING))
        ).findFirst();
        if(!opMainClass.isPresent()){
            throw new IOException("No Main class");
        }
        Method<? extends CommonCustomClass> method = opMainClass
                .get()
                .getMethods()
                .stream()
                .filter(m -> m.getName().equals("main"))
                .filter(m -> m.isStatic())
                .filter(m -> !m.getReturn().isPresent())
                .filter(m -> m.getParameters().size() == 1)
                .filter(m -> m.getParameters().get(0).getReturn().isPresent())
                .filter(m -> m.getParameters().get(0).getReturn().get() instanceof ArrayClass)
                .filter(m -> ((ArrayClass)m.getParameters().get(0).getReturn().get()).getNoneArrayClass().isMatch(ZeroPackageJavaClass.STRING))
                .findFirst()
                .get();

        File classFolder = new File(folder.getAbsolutePath(), "class");
        classFolder.mkdirs();
        String compileCmd = "javac -d " + classFolder.getAbsolutePath() + " " + ArrayUtils.toString(" ", f -> f.getAbsolutePath(), javaFiles);
        Process compileProcess = Runtime.getRuntime().exec(compileCmd);
        BufferedReader compileBR = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()));
        String compileLine;
        while((compileLine = compileBR.readLine()) != null){
            System.err.println(compileLine);
        }
        try {
            compileProcess.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*File manifest = new File(folder, "/manifest/Manifest.txt");
        FileWriter manWriter = new FileWriter(manifest);
        manWriter.write("Class-Path");*/

        Set<File> compiledClasses = new HashSet<>();
        javaFiles.stream().forEach(file -> {
            File newFile = new File(folder, "class/" + file.getPath().substring(10, file.getPath().length() - 5) + ".class");
            compiledClasses.add(newFile);
        });

        String buildCommand = "jar cfe " + fileName + " " + opMainClass.get().getPackageString() + "." + opMainClass.get().getName() + " " + ArrayUtils.toString(" ", f -> f.getAbsolutePath(), compiledClasses);
        Process buildProcess = Runtime.getRuntime().exec(buildCommand);
        BufferedReader buildBR = new BufferedReader(new InputStreamReader(buildProcess.getErrorStream()));
        String buildLine;
        while((buildLine = buildBR.readLine()) != null){
            System.err.println(buildLine);
        }
        try {
            buildProcess.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new File(fileName);
    }

}
