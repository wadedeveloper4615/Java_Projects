package com.wade.app;

public class Decompiler {
    public static void main(String[] argv) {
        try {
            for (int i = 0; i < argv.length; i++) {
                if (argv[i].endsWith(".class")) {
                    JavaClass java_class = new ClassParser(argv[i]).parse();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
