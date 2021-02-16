package com.wade.decompiler;

import java.io.IOException;

import com.wade.decompiler.classfile.JavaClass;
import com.wade.decompiler.util.ClassPath;
import com.wade.decompiler.util.SyntheticRepository;

public abstract class Repository {
    private static com.wade.decompiler.util.Repository repository = SyntheticRepository.getInstance();

    public static JavaClass addClass(final JavaClass clazz) {
        final JavaClass old = repository.findClass(clazz.getClassName());
        repository.storeClass(clazz);
        return old;
    }

    public static void clearCache() {
        repository.clear();
    }

    public static JavaClass[] getInterfaces(final JavaClass clazz) throws ClassNotFoundException {
        return clazz.getAllInterfaces();
    }

    public static JavaClass[] getInterfaces(final String class_name) throws ClassNotFoundException {
        return getInterfaces(lookupClass(class_name));
    }

    public static com.wade.decompiler.util.Repository getRepository() {
        return repository;
    }

    public static JavaClass[] getSuperClasses(final JavaClass clazz) throws ClassNotFoundException {
        return clazz.getSuperClasses();
    }

    public static JavaClass[] getSuperClasses(final String class_name) throws ClassNotFoundException {
        final JavaClass jc = lookupClass(class_name);
        return getSuperClasses(jc);
    }

    public static boolean implementationOf(final JavaClass clazz, final JavaClass inter) throws ClassNotFoundException {
        return clazz.implementationOf(inter);
    }

    public static boolean implementationOf(final JavaClass clazz, final String inter) throws ClassNotFoundException {
        return implementationOf(clazz, lookupClass(inter));
    }

    public static boolean implementationOf(final String clazz, final JavaClass inter) throws ClassNotFoundException {
        return implementationOf(lookupClass(clazz), inter);
    }

    public static boolean implementationOf(final String clazz, final String inter) throws ClassNotFoundException {
        return implementationOf(lookupClass(clazz), lookupClass(inter));
    }

    public static boolean instanceOf(final JavaClass clazz, final JavaClass super_class) throws ClassNotFoundException {
        return clazz.instanceOf(super_class);
    }

    public static boolean instanceOf(final JavaClass clazz, final String super_class) throws ClassNotFoundException {
        return instanceOf(clazz, lookupClass(super_class));
    }

    public static boolean instanceOf(final String clazz, final JavaClass super_class) throws ClassNotFoundException {
        return instanceOf(lookupClass(clazz), super_class);
    }

    public static boolean instanceOf(final String clazz, final String super_class) throws ClassNotFoundException {
        return instanceOf(lookupClass(clazz), lookupClass(super_class));
    }

    public static JavaClass lookupClass(final Class<?> clazz) throws ClassNotFoundException {
        return repository.loadClass(clazz);
    }

    public static JavaClass lookupClass(final String class_name) throws ClassNotFoundException {
        return repository.loadClass(class_name);
    }

    public static ClassPath.ClassFile lookupClassFile(final String class_name) {
        try {
            final ClassPath path = repository.getClassPath();
            if (path == null) {
                return null;
            }
            return path.getClassFile(class_name);
        } catch (final IOException e) {
            return null;
        }
    }

    public static void removeClass(final JavaClass clazz) {
        repository.removeClass(clazz);
    }

    public static void removeClass(final String clazz) {
        repository.removeClass(repository.findClass(clazz));
    }

    public static void setRepository(final com.wade.decompiler.util.Repository rep) {
        repository = rep;
    }
}
