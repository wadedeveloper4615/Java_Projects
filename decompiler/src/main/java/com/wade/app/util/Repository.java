package com.wade.app.util;

import com.wade.app.classfile.JavaClass;
import com.wade.app.exception.ClassFormatException;

public interface Repository {
    void clear();

    JavaClass findClass(String className);

    ClassPath getClassPath();

    JavaClass loadClass(Class<?> clazz) throws java.lang.ClassNotFoundException, ClassFormatException;

    JavaClass loadClass(String className) throws java.lang.ClassNotFoundException, ClassFormatException;

    void removeClass(JavaClass clazz);

    void storeClass(JavaClass clazz);
}
