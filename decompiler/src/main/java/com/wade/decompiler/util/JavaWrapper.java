package com.wade.decompiler.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class JavaWrapper {
    private final java.lang.ClassLoader loader;

    public JavaWrapper() {
        this(getClassLoader());
    }

    public JavaWrapper(final java.lang.ClassLoader loader) {
        this.loader = loader;
    }

    public void runMain(final String class_name, final String[] argv) throws ClassNotFoundException {
        final Class<?> cl = loader.loadClass(class_name);
        Method method = null;
        try {
            method = cl.getMethod("main", argv.getClass());
            final int m = method.getModifiers();
            final Class<?> r = method.getReturnType();
            if (!(Modifier.isPublic(m) && Modifier.isStatic(m)) || Modifier.isAbstract(m) || (r != Void.TYPE)) {
                throw new NoSuchMethodException();
            }
        } catch (final NoSuchMethodException no) {
            System.out.println("In class " + class_name + ": public static void main(String[] argv) is not defined");
            return;
        }
        try {
            method.invoke(null, argv);
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
    }

    private static java.lang.ClassLoader getClassLoader() {
        final String s = System.getProperty("bcel.classloader");
        if ((s == null) || "".equals(s)) {
            throw new IllegalStateException("The property 'bcel.classloader' must be defined");
        }
        try {
            return (java.lang.ClassLoader) Class.forName(s).newInstance();
        } catch (final Exception e) {
            throw new IllegalStateException(e.toString(), e);
        }
    }

    public static void main(final String[] argv) throws Exception {
        if (argv.length == 0) {
            System.out.println("Missing class name.");
            return;
        }
        final String class_name = argv[0];
        final String[] new_argv = new String[argv.length - 1];
        System.arraycopy(argv, 1, new_argv, 0, new_argv.length);
        final JavaWrapper wrapper = new JavaWrapper();
        wrapper.runMain(class_name, new_argv);
    }
}
