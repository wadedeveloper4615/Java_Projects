package org.apache.bcel.util;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import org.apache.bcel.classfile.JavaClass;

public class MemorySensitiveClassPathRepository extends AbstractClassPathRepository {
    private final Map<String, SoftReference<JavaClass>> loadedClasses = new HashMap<>();

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
        loadedClasses.put(clazz.getClassName().getName(), new SoftReference<>(clazz));
        clazz.setRepository(this);
    }
}
