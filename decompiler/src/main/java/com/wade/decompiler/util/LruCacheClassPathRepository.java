package com.wade.decompiler.util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.wade.decompiler.classfile.JavaClass;

public class LruCacheClassPathRepository extends AbstractClassPathRepository {
    private final LinkedHashMap<String, JavaClass> loadedClasses;

    public LruCacheClassPathRepository(final ClassPath path, final int cacheSize) {
        super(path);
        if (cacheSize < 1) {
            throw new IllegalArgumentException("cacheSize must be a positive number.");
        }
        final int initialCapacity = (int) (0.75 * cacheSize);
        final boolean accessOrder = true; // Evicts least-recently-accessed
        loadedClasses = new LinkedHashMap<String, JavaClass>(initialCapacity, cacheSize, accessOrder) {
            private static final long serialVersionUID = 1L;

            @Override
            protected boolean removeEldestEntry(final Map.Entry<String, JavaClass> eldest) {
                return size() > cacheSize;
            }
        };
    }

    @Override
    public void clear() {
        loadedClasses.clear();
    }

    @Override
    public JavaClass findClass(final String className) {
        return loadedClasses.get(className);
    }

    @Override
    public void removeClass(final JavaClass javaClass) {
        loadedClasses.remove(javaClass.getClassName());
    }

    @Override
    public void storeClass(final JavaClass javaClass) {
        // Not storing parent's _loadedClass
        loadedClasses.put(javaClass.getClassName(), javaClass);
        javaClass.setRepository(this);
    }
}
