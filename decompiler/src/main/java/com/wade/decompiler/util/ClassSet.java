package com.wade.decompiler.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.wade.decompiler.classfile.JavaClass;

public class ClassSet {
    private final Map<String, JavaClass> map = new HashMap<>();

    public boolean add(final JavaClass clazz) {
        boolean result = false;
        if (!map.containsKey(clazz.getClassName())) {
            result = true;
            map.put(clazz.getClassName(), clazz);
        }
        return result;
    }

    public boolean empty() {
        return map.isEmpty();
    }

    public String[] getClassNames() {
        return map.keySet().toArray(new String[map.size()]);
    }

    public void remove(final JavaClass clazz) {
        map.remove(clazz.getClassName());
    }

    public JavaClass[] toArray() {
        final Collection<JavaClass> values = map.values();
        final JavaClass[] classes = new JavaClass[values.size()];
        values.toArray(classes);
        return classes;
    }
}
