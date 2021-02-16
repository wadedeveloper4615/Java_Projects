package com.wade.decompiler.util;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import com.wade.decompiler.classfile.JavaClass;

public class MemorySensitiveClassPathRepository extends AbstractClassPathRepository {
    private final Map<String, SoftReference<JavaClass>> loadedClasses = new HashMap<>(); // CLASSNAME X JAVACLASS

    public MemorySensitiveClassPathRepository(final ClassPath path) {
        super(path);
    }

    @Override
    public void clear() {
        loadedClasses.clear();
    }

    @Override
    public JavaClass findClass(final String className) {
        final SoftReference<JavaClass> ref = loadedClasses.get(className);
        if (ref == null) {
            return null;
        }
        return ref.get();
    }

    @Override
    public void removeClass(final JavaClass clazz) {
        loadedClasses.remove(clazz.getClassName());
    }

    @Override
    public void storeClass(final JavaClass clazz) {
        // Not calling super.storeClass because this subclass maintains the mapping.
        loadedClasses.put(clazz.getClassName(), new SoftReference<>(clazz));
        clazz.setRepository(this);
    }
}
