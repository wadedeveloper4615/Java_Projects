package org.apache.bcel.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.bcel.classfile.JavaClass;

public class ClassPathRepository extends AbstractClassPathRepository {
    private final Map<String, JavaClass> _loadedClasses = new HashMap<>(); // CLASSNAME X JAVACLASS

    public ClassPathRepository(final ClassPath classPath) {
        super(classPath);
    }

    @Override
    public void clear() {
        _loadedClasses.clear();
    }

    @Override
    public JavaClass findClass(final String className) {
        return _loadedClasses.get(className);
    }

    @Override
    public void removeClass(final JavaClass javaClass) {
        _loadedClasses.remove(javaClass.getClassName());
    }

    @Override
    public void storeClass(final JavaClass javaClass) {
        _loadedClasses.put(javaClass.getClassName().getName(), javaClass);
        javaClass.setRepository(this);
    }
}
