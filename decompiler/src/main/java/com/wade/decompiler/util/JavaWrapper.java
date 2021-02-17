package com.wade.decompiler.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class JavaWrapper {
    private java.lang.ClassLoader loader;

    public JavaWrapper() {
        this(getClassLoader());
    }

    public JavaWrapper(java.lang.ClassLoader loader) {
        this.loader = loader;
    }

    public void runMain(String class_name, String[] argv) throws ClassNotFoundException {
        Class<?> cl = loader.loadClass(class_name);
        Method method = null;
        try {
            method = cl.getMethod("main", argv.getClass());
            int m = method.getModifiers();
            Class<?> r = method.getReturnType();
            if (!(Modifier.isPublic(m) && Modifier.isStatic(m)) || Modifier.isAbstract(m) || (r != Void.TYPE)) {
                throw new NoSuchMethodException();
            }
        } catch (NoSuchMethodException no) {
            System.out.println("In class " + class_name + ": public static void main(String[] argv) is not defined");
            return;
        }
        try {
            method.invoke(null, argv);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    private static java.lang.ClassLoader getClassLoader() {
        String s = System.getProperty("bcel.classloader");
        if ((s == null) || "".equals(s)) {
            throw new IllegalStateException("The property 'bcel.classloader' must be defined");
        }
        try {
            return (java.lang.ClassLoader) Class.forName(s).newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e.toString(), e);
        }
    }

    public static void main(String[] argv) throws Exception {
        if (argv.length == 0) {
            System.out.println("Missing class name.");
            return;
        }
        String class_name = argv[0];
        String[] new_argv = new String[argv.length - 1];
        System.arraycopy(argv, 1, new_argv, 0, new_argv.length);
        JavaWrapper wrapper = new JavaWrapper();
        wrapper.runMain(class_name, new_argv);
    }
}
