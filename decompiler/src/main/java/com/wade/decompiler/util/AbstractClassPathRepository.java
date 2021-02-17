package com.wade.decompiler.util;

import java.io.IOException;
import java.io.InputStream;

import com.wade.decompiler.classfile.ClassParser;
import com.wade.decompiler.classfile.JavaClass;

abstract class AbstractClassPathRepository implements Repository {
    private ClassPath _path;

    AbstractClassPathRepository(ClassPath classPath) {
        _path = classPath;
    }

    @Override
    public abstract void clear();

    @Override
    public abstract JavaClass findClass(String className);

    @Override
    public ClassPath getClassPath() {
        return _path;
    }

    @Override
    public JavaClass loadClass(Class<?> clazz) throws ClassNotFoundException {
        String className = clazz.getName();
        JavaClass repositoryClass = findClass(className);
        if (repositoryClass != null) {
            return repositoryClass;
        }
        String name = className;
        int i = name.lastIndexOf('.');
        if (i > 0) {
            name = name.substring(i + 1);
        }
        try (InputStream clsStream = clazz.getResourceAsStream(name + ".class")) {
            return loadClass(clsStream, className);
        } catch (IOException e) {
            return null;
        }
    }

    private JavaClass loadClass(InputStream inputStream, String className) throws ClassNotFoundException {
        try {
            if (inputStream != null) {
                ClassParser parser = new ClassParser(inputStream, className);
                JavaClass clazz = parser.parse();
                storeClass(clazz);
                return clazz;
            }
        } catch (IOException e) {
            throw new ClassNotFoundException("Exception while looking for class " + className + ": " + e, e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // ignored
                }
            }
        }
        throw new ClassNotFoundException("ClassRepository could not load " + className);
    }

    @Override
    public JavaClass loadClass(String className) throws ClassNotFoundException {
        if (className == null || className.isEmpty()) {
            throw new IllegalArgumentException("Invalid class name " + className);
        }
        className = className.replace('/', '.'); // Just in case, canonical form
        JavaClass clazz = findClass(className);
        if (clazz != null) {
            return clazz;
        }
        try {
            return loadClass(_path.getInputStream(className), className);
        } catch (IOException e) {
            throw new ClassNotFoundException("Exception while looking for class " + className + ": " + e, e);
        }
    }

    @Override
    public abstract void removeClass(JavaClass javaClass);

    @Override
    public abstract void storeClass(JavaClass javaClass);
}
