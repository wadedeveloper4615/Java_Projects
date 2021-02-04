package com.wade.app.classfile;

import com.wade.app.Const;
import com.wade.app.exception.ClassFormatException;

public class ObjectType extends ReferenceType {
    private final String className; // Class name of type

    public ObjectType(final String className) {
        super(Const.T_REFERENCE, "L" + className.replace('.', '/') + ";");
        this.className = className.replace('/', '.');
    }

    public boolean accessibleTo(final ObjectType accessor) throws ClassNotFoundException, ClassFormatException {
        final JavaClass jc = Repository.lookupClass(className);
        if (jc.isPublic()) {
            return true;
        }
        final JavaClass acc = Repository.lookupClass(accessor.className);
        return acc.getPackageName().equals(jc.getPackageName());
    }

    @Override
    public boolean equals(final Object type) {
        return (type instanceof ObjectType) ? ((ObjectType) type).className.equals(className) : false;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public int hashCode() {
        return className.hashCode();
    }

    public boolean referencesClass() throws ClassFormatException {
        try {
            final JavaClass jc = Repository.lookupClass(className);
            return jc.isClass();
        } catch (final ClassNotFoundException e) {
            return false;
        }
    }

    public boolean referencesClassExact() throws ClassNotFoundException, ClassFormatException {
        final JavaClass jc = Repository.lookupClass(className);
        return jc.isClass();
    }

    public boolean referencesInterface() throws ClassFormatException {
        try {
            final JavaClass jc = Repository.lookupClass(className);
            return !jc.isClass();
        } catch (final ClassNotFoundException e) {
            return false;
        }
    }

    public boolean referencesInterfaceExact() throws ClassNotFoundException, ClassFormatException {
        final JavaClass jc = Repository.lookupClass(className);
        return !jc.isClass();
    }

    public boolean subclassOf(final ObjectType superclass) throws ClassNotFoundException, ClassFormatException {
        if (this.referencesInterfaceExact() || superclass.referencesInterfaceExact()) {
            return false;
        }
        return Repository.instanceOf(this.className, superclass.className);
    }

    public static ObjectType getInstance(final String className) {
        return new ObjectType(className);
    }
}
