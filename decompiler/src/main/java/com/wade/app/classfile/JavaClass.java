package com.wade.app.classfile;

import java.util.Arrays;

import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.Version;
import com.wade.app.util.ClassAccessFlagsList;
import com.wade.app.util.FieldsList;
import com.wade.app.util.InterfacesList;

public class JavaClass {
    private Version version;
    private ConstantPool constantPool;
    private ClassFileName className;
    private ClassFileName superclassName;
    private ClassAccessFlagsList accessFlags;
    private InterfacesList interfaces;
    private FieldsList fields;
    private MethodsList methods;
    private Attribute[] attributes;

    public JavaClass(Version version, ConstantPool constantPool, ClassAccessFlagsList accessFlags, ClassFileName className, ClassFileName superclassName, InterfacesList interfaces, FieldsList fields, MethodsList methods, Attribute[] attributes) {
        this.version = version;
        this.constantPool = constantPool;
        this.accessFlags = accessFlags;
        this.className = className;
        this.superclassName = superclassName;
        this.interfaces = interfaces;
        this.fields = fields;
        this.methods = methods;
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "JavaClass [version=" + version + ", \nconstantPool=" + constantPool + ", \nclassName=" + className + ", \nsuperclassName=" + superclassName + ", \naccessFlags=" + accessFlags + ", \ninterfaces=" + interfaces + ", \nfields=" + fields + ", \nmethods=" + methods + ", \nattributes=" + Arrays.toString(attributes) + "]";
    }
}
