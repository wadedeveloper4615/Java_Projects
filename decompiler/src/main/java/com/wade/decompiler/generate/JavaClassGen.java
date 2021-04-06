package com.wade.decompiler.generate;

import java.io.IOException;

import com.wade.decompiler.classfile.Field;
import com.wade.decompiler.classfile.JavaClass;
import com.wade.decompiler.classfile.Method;
import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.Version;
import com.wade.decompiler.generate.attribute.AttributeGen;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode
public class JavaClassGen {
    private String className;
    private String superClassName;
    private String filename;
    private Version version;
    private ClassAccessFlagsList accessFlags;
    private String[] interfaceNames;
    private FieldGen[] fields;
    private MethodGen[] methods;
    private AttributeGen[] attributes;
    private ConstantPool constantPool;

    public JavaClassGen(JavaClass javaClass) throws IOException {
        this.constantPool = javaClass.getConstantPool();
        this.className = constantPool.constantToString(javaClass.getClassNameIndex(), ClassFileConstants.CONSTANT_Class);
        if (javaClass.getSuperclassNameIndex() != 0) {
            this.superClassName = constantPool.constantToString(javaClass.getSuperclassNameIndex(), ClassFileConstants.CONSTANT_Class);
        } else {
            this.superClassName = "";
        }
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

        Attribute[] attributes = javaClass.getAttributes();
        this.attributes = new AttributeGen[attributes.length];
        for (int i = 0; i < attributes.length; i++) {
            this.attributes[i] = AttributeGen.readAttribute(attributes[i], constantPool);
        }
    }
}