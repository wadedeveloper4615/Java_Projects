package com.wade.decompiler.gen;

import java.util.Arrays;

import com.wade.decompiler.classfile.JavaClass;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.Version;

public class JavaClassGen {
    private String className;
    private String superClassName;
    private String filename;
    private Version version;
    private ClassAccessFlagsList accessFlags;
    private String[] interfaceNames;

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
    }

    @Override
    public String toString() {
        return "JavaClassGen [className=" + className + ", superClassName=" + superClassName + ", filename=" + filename + ", version=" + version + ", accessFlags=" + accessFlags + ", interfaceNames=" + Arrays.toString(interfaceNames) + "]";
    }
}
