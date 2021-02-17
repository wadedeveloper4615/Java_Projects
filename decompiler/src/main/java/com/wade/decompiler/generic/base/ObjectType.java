package com.wade.decompiler.generic.base;

import com.wade.decompiler.Const;
import com.wade.decompiler.Repository;
import com.wade.decompiler.classfile.JavaClass;

public class ObjectType extends ReferenceType {
    private final String className; // Class name of type

    public ObjectType(final String className) {
        super(Const.T_REFERENCE, "L" + className.replace('.', '/') + ";");
        this.className = className.replace('/', '.');
    }

    public boolean accessibleTo(final ObjectType accessor) throws ClassNotFoundException {
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

    @Deprecated
    public boolean referencesClass() {
        try {
            final JavaClass jc = Repository.lookupClass(className);
            return jc.isClass();
        } catch (final ClassNotFoundException e) {
            return false;
        }
    }

    public boolean referencesClassExact() throws ClassNotFoundException {
        final JavaClass jc = Repository.lookupClass(className);
        return jc.isClass();
    }

    @Deprecated
    public boolean referencesInterface() {
        try {
            final JavaClass jc = Repository.lookupClass(className);
            return !jc.isClass();
        } catch (final ClassNotFoundException e) {
            return false;
        }
    }

    public boolean referencesInterfaceExact() throws ClassNotFoundException {
        final JavaClass jc = Repository.lookupClass(className);
        return !jc.isClass();
    }

    public boolean subclassOf(final ObjectType superclass) throws ClassNotFoundException {
        if (this.referencesInterfaceExact() || superclass.referencesInterfaceExact()) {
            return false;
        }
        return Repository.instanceOf(this.className, superclass.className);
    }

    public static ObjectType getInstance(final String className) {
        return new ObjectType(className);
    }
}
