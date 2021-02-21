package com.wade.decompiler.classfile;

import java.util.Arrays;

import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.Version;
import com.wade.decompiler.util.Utility;

public class JavaClass {
    private int classNameIndex;
    private int superclassNameIndex;
    private String fileName;
    private Version version;
    private ClassAccessFlagsList accessFlags;
    private ConstantPool constantPool;
    private int[] interfaces;
    private Field[] fields;
    private Method[] methods;
    private Attribute[] attributes;

    public JavaClass(int classNameIndex, int superclassNameIndex, String fileName, Version version, ClassAccessFlagsList accessFlags, ConstantPool constantPool, int[] interfaces, Field[] fields, Method[] methods, Attribute[] attributes) {
        this.classNameIndex = classNameIndex;
        this.superclassNameIndex = superclassNameIndex;
        this.fileName = fileName;
        this.version = version;
        this.accessFlags = accessFlags;
        this.constantPool = constantPool;
        this.interfaces = interfaces;
        this.fields = fields;
        this.methods = methods;
        this.attributes = attributes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JavaClass other = (JavaClass) obj;
        if (accessFlags == null) {
            if (other.accessFlags != null)
                return false;
        } else if (!accessFlags.equals(other.accessFlags))
            return false;
        if (!Arrays.equals(attributes, other.attributes))
            return false;
        if (classNameIndex != other.classNameIndex)
            return false;
        if (constantPool == null) {
            if (other.constantPool != null)
                return false;
        } else if (!constantPool.equals(other.constantPool))
            return false;
        if (!Arrays.equals(fields, other.fields))
            return false;
        if (fileName == null) {
            if (other.fileName != null)
                return false;
        } else if (!fileName.equals(other.fileName))
            return false;
        if (!Arrays.equals(interfaces, other.interfaces))
            return false;
        if (!Arrays.equals(methods, other.methods))
            return false;
        if (superclassNameIndex != other.superclassNameIndex)
            return false;
        if (version != other.version)
            return false;
        return true;
    }

    public ClassAccessFlagsList getAccessFlags() {
        return accessFlags;
    }

    public Attribute[] getAttributes() {
        return attributes;
    }

    public int getClassNameIndex() {
        return classNameIndex;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public Field[] getFields() {
        return fields;
    }

    public String getFileName() {
        return fileName;
    }

    public int[] getInterfaces() {
        return interfaces;
    }

    public Method[] getMethods() {
        return methods;
    }

    public int getSuperclassNameIndex() {
        return superclassNameIndex;
    }

    public Version getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accessFlags == null) ? 0 : accessFlags.hashCode());
        result = prime * result + Arrays.hashCode(attributes);
        result = prime * result + classNameIndex;
        result = prime * result + ((constantPool == null) ? 0 : constantPool.hashCode());
        result = prime * result + Arrays.hashCode(fields);
        result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
        result = prime * result + Arrays.hashCode(interfaces);
        result = prime * result + Arrays.hashCode(methods);
        result = prime * result + superclassNameIndex;
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("" + constantPool);
        buffer.append("Class Name Index       = " + classNameIndex + "\n");
        buffer.append("Super Class Name Index = " + superclassNameIndex + "\n");
        buffer.append("File Name              = " + fileName + "\n");
        buffer.append("Version                = " + version + "\n");
        buffer.append("Access Flags           = " + accessFlags.getFlags() + "\n");
        buffer.append("Interfaces             = " + Utility.toString(interfaces) + "\n");
        buffer.append("Fields                 = " + Utility.toString(fields) + "\n");
        buffer.append("Methods                = " + Utility.toString(methods) + "\n");
        return buffer.toString();
    }

    public String toString2() {
        return "JavaClass [interfaces=" + Utility.toString(interfaces) + ", fields=" + Utility.toString(fields) + ", methods=" + Utility.toString(methods) + ", attributes=" + Utility.toString(attributes) + "]";
    }
}
