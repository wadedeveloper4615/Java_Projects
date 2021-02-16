package com.wade.decompiler.util;

import com.wade.decompiler.classfile.JavaClass;

public interface Repository {
    void clear();

    JavaClass findClass(String className);

    ClassPath getClassPath();

    JavaClass loadClass(Class<?> clazz) throws java.lang.ClassNotFoundException;

    JavaClass loadClass(String className) throws java.lang.ClassNotFoundException;

    void removeClass(JavaClass clazz);

    void storeClass(JavaClass clazz);
}
