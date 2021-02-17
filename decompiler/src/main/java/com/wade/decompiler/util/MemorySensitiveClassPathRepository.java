package com.wade.decompiler.util;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import com.wade.decompiler.classfile.JavaClass;

public class MemorySensitiveClassPathRepository extends AbstractClassPathRepository {
    private Map<String, SoftReference<JavaClass>> loadedClasses = new HashMap<>(); // CLASSNAME X JAVACLASS

    public MemorySensitiveClassPathRepository(ClassPath path) {
        super(path);
    }

    @Override
    public void clear() {
        loadedClasses.clear();
    }

    @Override
    public JavaClass findClass(String className) {
        SoftReference<JavaClass> ref = loadedClasses.get(className);
        if (ref == null) {
            return null;
        }
        return ref.get();
    }

    @Override
    public void removeClass(JavaClass clazz) {
        loadedClasses.remove(clazz.getClassName());
    }

    @Override
    public void storeClass(JavaClass clazz) {
        // Not calling super.storeClass because this subclass maintains the mapping.
        loadedClasses.put(clazz.getClassName(), new SoftReference<>(clazz));
        clazz.setRepository(this);
    }
}
