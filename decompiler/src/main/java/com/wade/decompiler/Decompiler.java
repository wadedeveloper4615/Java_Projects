package com.wade.decompiler;

import java.io.InputStream;

import com.wade.decompiler.classfile.ClassParser;
import com.wade.decompiler.classfile.JavaClass;
import com.wade.decompiler.gen.JavaClassGen;

public class Decompiler {
    private void decompile(JavaClass javaClass) {
        JavaClassGen jgen = new JavaClassGen(javaClass);
        System.out.println(javaClass.toString());
        System.out.println(jgen.toString());
    }

    public static void main(String[] argv) {
        try {
            Class<Decompiler> c = Decompiler.class;
            String resource = "/com/wade/decompiler/test/Test1.class";
            InputStream rs = c.getResourceAsStream(resource);
            JavaClass javaClass = new ClassParser(rs, "Test1.class").parse();
            new Decompiler().decompile(javaClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
