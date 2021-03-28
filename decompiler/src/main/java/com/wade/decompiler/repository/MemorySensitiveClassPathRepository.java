package com.wade.decompiler.repository;

import com.wade.decompiler.classfile.JavaClass;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class MemorySensitiveClassPathRepository extends AbstractClassPathRepository {
    private final Map<String, SoftReference<JavaClass>> loadedClasses = new HashMap<>(); // CLASSNAME X JAVACLASS

    public MemorySensitiveClassPathRepository(final ClassPath path) {
        super(path);
    }

    @Override
    public JavaClass findClass(String className) {
        final SoftReference<JavaClass> ref = loadedClasses.get(className);
        if (ref == null) {
            return null;
        }
        return ref.get();
    }

    @Override
    public void storeClass(JavaClass clazz) {
        loadedClasses.put(clazz.getClassName(), new SoftReference<>(clazz));
        clazz.setRepository(this);
    }
}
