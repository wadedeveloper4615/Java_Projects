package com.wade.decompiler.generate;

import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.JavaClass;
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
    private List<String> interfaceNames;
    private List<FieldGen> fields;
    private List<MethodGen> methods;
    private List<AttributeGen> attributes;
    private ConstantPool constantPool;

    public JavaClassGen(JavaClass javaClass) throws Exception {
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

        this.interfaceNames = new ArrayList<>();
        javaClass.getInterfaces().stream().forEach(interfaceNameIndex -> interfaceNames.add(constantPool.constantToString(interfaceNameIndex, ClassFileConstants.CONSTANT_Class)));

        this.fields = new ArrayList<>();
        javaClass.getFields().stream().forEach(field -> fields.add(new FieldGen(field, constantPool)));

        this.methods = new ArrayList<>();
        javaClass.getMethods().stream().forEach(method -> methods.add(new MethodGen(method, constantPool)));

        this.attributes = new ArrayList<>();
        javaClass.getAttributes().stream().forEach(attribute -> attributes.add(AttributeGen.readAttribute(attribute, constantPool)));
    }
}