package com.wade.decompiler.repository;

import com.wade.decompiler.classfile.JavaClass;

public interface Repository {
    JavaClass findClass(String className);

    JavaClass loadClass(Class<?> clazz) throws java.lang.ClassNotFoundException;

    JavaClass loadClass(String className) throws java.lang.ClassNotFoundException;

    void storeClass(JavaClass clazz);
}
