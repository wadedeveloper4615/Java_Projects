package org.apache.bcel;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;

public class Decompiler {
    public static void main(String[] argv) {
        try {
            for (int i = 0; i < argv.length; i++) {
                if (argv[i].endsWith(".class")) {
                    JavaClass javaClass = new ClassParser(argv[i]).parse();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
