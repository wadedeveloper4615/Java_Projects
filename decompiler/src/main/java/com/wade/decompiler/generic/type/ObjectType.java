package com.wade.decompiler.generic.type;

import com.wade.decompiler.classfile.JavaClass;
import com.wade.decompiler.enums.TypeEnum;
import com.wade.decompiler.util.AbstractRepository;

public class ObjectType extends ReferenceType {
    private String className; // Class name of type

    public ObjectType(String className) {
        super(TypeEnum.T_REFERENCE, "L" + className.replace('.', '/') + ";");
        this.className = className.replace('/', '.');
    }

    public boolean accessibleTo(ObjectType accessor) throws ClassNotFoundException {
        JavaClass jc = AbstractRepository.lookupClass(className);
        if (jc.isPublic()) {
            return true;
        }
        JavaClass acc = AbstractRepository.lookupClass(accessor.className);
        return acc.getPackageName().equals(jc.getPackageName());
    }

    @Override
    public boolean equals(Object type) {
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
            JavaClass jc = AbstractRepository.lookupClass(className);
            return jc.isClass();
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public boolean referencesClassExact() throws ClassNotFoundException {
        JavaClass jc = AbstractRepository.lookupClass(className);
        return jc.isClass();
    }

    @Deprecated
    public boolean referencesInterface() {
        try {
            JavaClass jc = AbstractRepository.lookupClass(className);
            return !jc.isClass();
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public boolean referencesInterfaceExact() throws ClassNotFoundException {
        JavaClass jc = AbstractRepository.lookupClass(className);
        return !jc.isClass();
    }

    public boolean subclassOf(ObjectType superclass) throws ClassNotFoundException {
        if (this.referencesInterfaceExact() || superclass.referencesInterfaceExact()) {
            return false;
        }
        return AbstractRepository.instanceOf(this.className, superclass.className);
    }

    public static ObjectType getInstance(String className) {
        return new ObjectType(className);
    }
}
