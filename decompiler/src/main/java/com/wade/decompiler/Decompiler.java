package com.wade.decompiler;

import java.io.InputStream;

import com.wade.decompiler.classfile.ClassParser;
import com.wade.decompiler.classfile.JavaClass;
import com.wade.decompiler.decompiler.JavaClassFileDecompiler;
import com.wade.decompiler.generate.JavaClassGen;

public class Decompiler {
    private void decompile(JavaClass javaClass) throws Exception {
        JavaClassGen jgen = new JavaClassGen(javaClass);
        JavaClassFileDecompiler jcfd = new JavaClassFileDecompiler(jgen);
        jcfd.toString();
    }

    public static void main(String[] argv) {
        try {
            Class<Decompiler> c = Decompiler.class;
            // String resource = "/com/wade/decompiler/test/MyCustomAnnotation.class";
            // String resource = "/com/wade/decompiler/test/Test2.class";
            String resource = "/com/wade/decompiler/test/Test1.class";
            // String resource = "/java/lang/Object.class";
            InputStream rs = c.getResourceAsStream(resource);
            JavaClass javaClass = new ClassParser(rs, "Test1.class").parse();
            new Decompiler().decompile(javaClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
