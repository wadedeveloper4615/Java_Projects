package com.wade.decompiler.util;

import java.util.HashMap;
import java.util.Map;

import com.wade.decompiler.classfile.JavaClass;

public class ClassPathRepository extends AbstractClassPathRepository {
    private Map<String, JavaClass> _loadedClasses = new HashMap<>(); // CLASSNAME X JAVACLASS

    public ClassPathRepository(ClassPath classPath) {
        super(classPath);
    }

    @Override
    public void clear() {
        _loadedClasses.clear();
    }

    @Override
    public JavaClass findClass(String className) {
        return _loadedClasses.get(className);
    }

    @Override
    public void removeClass(JavaClass javaClass) {
        _loadedClasses.remove(javaClass.getClassName());
    }

    @Override
    public void storeClass(JavaClass javaClass) {
        _loadedClasses.put(javaClass.getClassName(), javaClass);
        javaClass.setRepository(this);
    }
}
