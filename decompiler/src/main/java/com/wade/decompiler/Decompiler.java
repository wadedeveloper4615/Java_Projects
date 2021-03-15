package com.wade.decompiler;

import java.io.IOException;
import java.io.InputStream;

import com.wade.decompiler.classfile.ClassParser;
import com.wade.decompiler.classfile.JavaClass;
import com.wade.decompiler.decompiler.JavaClassFileDecompiler;
import com.wade.decompiler.generate.JavaClassGen;

public class Decompiler {
    protected void decompile(JavaClass javaClass) throws Exception {
        JavaClassGen jgen = new JavaClassGen(javaClass);
        JavaClassFileDecompiler jcfd = new JavaClassFileDecompiler(jgen);
        jcfd.toString();
    }

    protected JavaClass process(Class<Decompiler> parent, String resource) throws IOException, Exception {
        InputStream rs = parent.getResourceAsStream(resource);
        return new ClassParser(rs, resource).parse();
    }

    public static void main(String[] argv) {
        try {
            Class<Decompiler> c = Decompiler.class;
            Decompiler decompiler = new Decompiler();
            // String resource = "/com/wade/decompiler/test/MyCustomAnnotation.class";
            // String resource = "/com/wade/decompiler/test/Test1.class";
            // String resource = "/com/wade/decompiler/test/Test2.class";
            // String resource = "/com/wade/decompiler/test/Test3.class";
            // String resource = "/com/wade/decompiler/test/Test4.class";
            String resource = "/com/wade/decompiler/test/Test5.class";
            // String resource = "/com/wade/decompiler/test/Test6.class";
            // String resource = "/java/lang/Object.class";
            // JavaClass javaClass = new ClassParser(argv[0]).parse();
            JavaClass javaClass = decompiler.process(c, resource);
            decompiler.decompile(javaClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
