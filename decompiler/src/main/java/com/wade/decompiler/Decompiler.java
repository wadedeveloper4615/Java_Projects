package com.wade.decompiler;

import com.wade.decompiler.classfile.ClassParser;
import com.wade.decompiler.classfile.JavaClass;

public class Decompiler {
    private void decompile(JavaClass javaClass) {
        System.out.println(javaClass.toString());
    }

    public static void main(String[] argv) {
        try {
            for (int i = 0; i < argv.length; i++) {
                if (argv[i].endsWith(".class")) {
                    JavaClass javaClass = new ClassParser(argv[i]).parse();
                    new Decompiler().decompile(javaClass);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
