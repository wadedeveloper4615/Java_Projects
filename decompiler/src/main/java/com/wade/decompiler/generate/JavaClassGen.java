package com.wade.decompiler.generate;

import java.util.Arrays;

import com.wade.decompiler.classfile.Field;
import com.wade.decompiler.classfile.JavaClass;
import com.wade.decompiler.classfile.Method;
import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.Version;
import com.wade.decompiler.util.Utility;

public class JavaClassGen {
    private String className;
    private String superClassName;
    private String filename;
    private Version version;
    private ClassAccessFlagsList accessFlags;
    private String[] interfaceNames;
    private FieldGen[] fields;
    private MethodGen[] methods;
    private Attribute[] attributes;

    public JavaClassGen(JavaClass javaClass) {
        ConstantPool constantPool = javaClass.getConstantPool();
        this.className = constantPool.constantToString(javaClass.getClassNameIndex(), ClassFileConstants.CONSTANT_Class);
        this.superClassName = constantPool.constantToString(javaClass.getSuperclassNameIndex(), ClassFileConstants.CONSTANT_Class);
        this.filename = javaClass.getFileName();
        this.version = javaClass.getVersion();
        this.accessFlags = javaClass.getAccessFlags();

        int[] interfaces = javaClass.getInterfaces();
        this.interfaceNames = new String[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            this.interfaceNames[i] = constantPool.constantToString(interfaces[i], ClassFileConstants.CONSTANT_Class);
        }

        Field[] fields = javaClass.getFields();
        this.fields = new FieldGen[fields.length];
        for (int i = 0; i < fields.length; i++) {
            this.fields[i] = new FieldGen(fields[i], constantPool);
        }

        Method[] methods = javaClass.getMethods();
        this.methods = new MethodGen[methods.length];
        for (int i = 0; i < methods.length; i++) {
            this.methods[i] = new MethodGen(methods[i], constantPool);
        }

        this.attributes = javaClass.getAttributes();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JavaClassGen other = (JavaClassGen) obj;
        if (accessFlags == null) {
            if (other.accessFlags != null)
                return false;
        } else if (!accessFlags.equals(other.accessFlags))
            return false;
        if (!Arrays.equals(attributes, other.attributes))
            return false;
        if (className == null) {
            if (other.className != null)
                return false;
        } else if (!className.equals(other.className))
            return false;
        if (!Arrays.equals(fields, other.fields))
            return false;
        if (filename == null) {
            if (other.filename != null)
                return false;
        } else if (!filename.equals(other.filename))
            return false;
        if (!Arrays.equals(interfaceNames, other.interfaceNames))
            return false;
        if (!Arrays.equals(methods, other.methods))
            return false;
        if (superClassName == null) {
            if (other.superClassName != null)
                return false;
        } else if (!superClassName.equals(other.superClassName))
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

    public String getClassName() {
        return className;
    }

    public FieldGen[] getFields() {
        return fields;
    }

    public String getFilename() {
        return filename;
    }

    public String[] getInterfaceNames() {
        return interfaceNames;
    }

    public MethodGen[] getMethods() {
        return methods;
    }

    public String getSuperClassName() {
        return superClassName;
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
        result = prime * result + ((className == null) ? 0 : className.hashCode());
        result = prime * result + Arrays.hashCode(fields);
        result = prime * result + ((filename == null) ? 0 : filename.hashCode());
        result = prime * result + Arrays.hashCode(interfaceNames);
        result = prime * result + Arrays.hashCode(methods);
        result = prime * result + ((superClassName == null) ? 0 : superClassName.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "JavaClassGen [\n\tclassName=" + className + ",\n\tsuperClassName=" + superClassName + ",\n\tfilename=" + filename + ",\n\tversion=" + version + ",\n\taccessFlags=" + accessFlags + ",\n\tinterfaceNames=" + Utility.toString(interfaceNames) + ",\n\tfields=" + Utility.toString(fields) + ",\n\tmethods=" + Utility.toString(methods) + ",\n\tattributes=" + Utility.toString(attributes) + "\n]";
    }
}