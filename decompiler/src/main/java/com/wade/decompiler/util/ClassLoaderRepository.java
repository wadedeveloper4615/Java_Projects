package com.wade.decompiler.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.wade.decompiler.classfile.ClassParser;
import com.wade.decompiler.classfile.JavaClass;

public class ClassLoaderRepository implements Repository {
    private java.lang.ClassLoader loader;
    private Map<String, JavaClass> loadedClasses = new HashMap<>(); // CLASSNAME X JAVACLASS

    public ClassLoaderRepository(java.lang.ClassLoader loader) {
        this.loader = loader;
    }

    @Override
    public void clear() {
        loadedClasses.clear();
    }

    @Override
    public JavaClass findClass(String className) {
        return loadedClasses.containsKey(className) ? loadedClasses.get(className) : null;
    }

    @Override
    public ClassPath getClassPath() {
        return null;
    }

    @Override
    public JavaClass loadClass(Class<?> clazz) throws ClassNotFoundException {
        return loadClass(clazz.getName());
    }

    @Override
    public JavaClass loadClass(String className) throws ClassNotFoundException {
        String classFile = className.replace('.', '/');
        JavaClass RC = findClass(className);
        if (RC != null) {
            return RC;
        }
        try (InputStream is = loader.getResourceAsStream(classFile + ".class")) {
            if (is == null) {
                throw new ClassNotFoundException(className + " not found.");
            }
            ClassParser parser = new ClassParser(is, className);
            RC = parser.parse();
            storeClass(RC);
            return RC;
        } catch (IOException e) {
            throw new ClassNotFoundException(className + " not found: " + e, e);
        }
    }

    @Override
    public void removeClass(JavaClass clazz) {
        loadedClasses.remove(clazz.getClassName());
    }

    @Override
    public void storeClass(JavaClass clazz) {
        loadedClasses.put(clazz.getClassName(), clazz);
        clazz.setRepository(this);
    }
}
