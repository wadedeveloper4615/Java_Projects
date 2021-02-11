
package org.apache.bcel.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;

public class ClassLoaderRepository implements Repository {

    private final java.lang.ClassLoader loader;
    private final Map<String, JavaClass> loadedClasses = new HashMap<>(); // CLASSNAME X JAVACLASS

    public ClassLoaderRepository(final java.lang.ClassLoader loader) {
        this.loader = loader;
    }

    @Override
    public void clear() {
        loadedClasses.clear();
    }

    @Override
    public JavaClass findClass(final String className) {
        return loadedClasses.containsKey(className) ? loadedClasses.get(className) : null;
    }

    @Override
    public ClassPath getClassPath() {
        return null;
    }

    @Override
    public JavaClass loadClass(final Class<?> clazz) throws ClassNotFoundException {
        return loadClass(clazz.getName());
    }

    @Override
    public JavaClass loadClass(final String className) throws ClassNotFoundException {
        final String classFile = className.replace('.', '/');
        JavaClass RC = findClass(className);
        if (RC != null) {
            return RC;
        }
        try (InputStream is = loader.getResourceAsStream(classFile + ".class")) {
            if (is == null) {
                throw new ClassNotFoundException(className + " not found.");
            }
            final ClassParser parser = new ClassParser(is, className);
            RC = parser.parse();
            storeClass(RC);
            return RC;
        } catch (final IOException e) {
            throw new ClassNotFoundException(className + " not found: " + e, e);
        }
    }

    @Override
    public void removeClass(final JavaClass clazz) {
        loadedClasses.remove(clazz.getClassName());
    }

    @Override
    public void storeClass(final JavaClass clazz) {
        loadedClasses.put(clazz.getClassName().getName(), clazz);
        clazz.setRepository(this);
    }
}
