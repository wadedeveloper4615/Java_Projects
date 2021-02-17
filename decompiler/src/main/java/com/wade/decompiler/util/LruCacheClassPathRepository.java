package com.wade.decompiler.util;

import java.util.LinkedHashMap;
import java.util.Map;

import com.wade.decompiler.classfile.JavaClass;

public class LruCacheClassPathRepository extends AbstractClassPathRepository {
    private LinkedHashMap<String, JavaClass> loadedClasses;

    public LruCacheClassPathRepository(ClassPath path, int cacheSize) {
        super(path);
        if (cacheSize < 1) {
            throw new IllegalArgumentException("cacheSize must be a positive number.");
        }
        int initialCapacity = (int) (0.75 * cacheSize);
        boolean accessOrder = true; // Evicts least-recently-accessed
        loadedClasses = new LinkedHashMap<String, JavaClass>(initialCapacity, cacheSize, accessOrder) {
            private static final long serialVersionUID = 1L;

            @Override
            protected boolean removeEldestEntry(Map.Entry<String, JavaClass> eldest) {
                return size() > cacheSize;
            }
        };
    }

    @Override
    public void clear() {
        loadedClasses.clear();
    }

    @Override
    public JavaClass findClass(String className) {
        return loadedClasses.get(className);
    }

    @Override
    public void removeClass(JavaClass javaClass) {
        loadedClasses.remove(javaClass.getClassName());
    }

    @Override
    public void storeClass(JavaClass javaClass) {
        // Not storing parent's _loadedClass
        loadedClasses.put(javaClass.getClassName(), javaClass);
        javaClass.setRepository(this);
    }
}
