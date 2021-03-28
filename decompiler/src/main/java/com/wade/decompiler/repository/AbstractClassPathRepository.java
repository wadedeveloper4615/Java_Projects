package com.wade.decompiler.repository;

import com.wade.decompiler.classfile.ClassParser;
import com.wade.decompiler.classfile.JavaClass;

import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractClassPathRepository implements Repository {
    private final ClassPath _path;

    public AbstractClassPathRepository(final ClassPath classPath) {
        _path = classPath;
    }

    @Override
    public abstract JavaClass findClass(final String className);

    @SuppressWarnings("unused")
    @Override
    public JavaClass loadClass(Class<?> clazz) throws ClassNotFoundException {
        final String className = clazz.getName();
        final JavaClass repositoryClass = findClass(className);
        if (repositoryClass != null) {
            return repositoryClass;
        }
        String name = className;
        final int i = name.lastIndexOf('.');
        if (i > 0) {
            name = name.substring(i + 1);
        }

        try (InputStream clsStream = clazz.getResourceAsStream(name + ".class")) {
            return loadClass(clsStream, className);
        } catch (final IOException e) {
            return null;
        }
    }

    @SuppressWarnings("unused")
    private JavaClass loadClass(final InputStream inputStream, final String className) throws ClassNotFoundException {
        try {
            if (inputStream != null) {
                final ClassParser parser = new ClassParser(inputStream, className);
                final JavaClass clazz = parser.parse();
                storeClass(clazz);
                return clazz;
            }
        } catch (final IOException e) {
            throw new ClassNotFoundException("Exception while looking for class " + className + ": " + e, e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (final IOException e) {
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
        final JavaClass clazz = findClass(className);
        if (clazz != null) {
            return clazz;
        }
        try {
            return loadClass(_path.getInputStream(className), className);
        } catch (final IOException e) {
            throw new ClassNotFoundException("Exception while looking for class " + className + ": " + e, e);
        }
    }

    @Override
    public abstract void storeClass(final JavaClass javaClass);
}
