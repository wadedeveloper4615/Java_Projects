package org.apache.bcel.util;

import org.apache.bcel.classfile.JavaClass;

public interface Repository {
    void storeClass(JavaClass clazz);

    void removeClass(JavaClass clazz);

    JavaClass findClass(String className);

    JavaClass loadClass(String className) throws java.lang.ClassNotFoundException;

    JavaClass loadClass(Class<?> clazz) throws java.lang.ClassNotFoundException;

    void clear();

    ClassPath getClassPath();
}
