package com.wade.app.classfile;

import java.util.Arrays;

import com.wade.app.classfile.attribute.Attribute;
import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.Version;
import com.wade.app.util.ClassAccessFlagsList;
import com.wade.app.util.FieldsList;
import com.wade.app.util.InterfacesList;
import com.wade.app.util.MethodsList;

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
        StringBuilder buffer = new StringBuilder("JavaClass [\n");
        buffer.append("\tVersion=" + version + ",\n");
        buffer.append("\tConstantPool=" + constantPool + ",\n");
        buffer.append("\tClassName=" + className + ",\n");
        buffer.append("\tSuperClassName=" + superclassName + ",\n");
        buffer.append("\tAccessFlags=" + accessFlags + ",\n");
        buffer.append("\tInterfaces=" + interfaces + ",\n");
        buffer.append("\tFields=" + fields + ",\n");
        buffer.append("\tMethods=" + methods + ",\n");
        buffer.append("\tAttributes=" + Arrays.toString(attributes) + ",\n");
        buffer.append("]\n");
        return buffer.toString();
    }
}
