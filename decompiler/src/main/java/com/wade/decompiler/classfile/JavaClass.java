package com.wade.decompiler.classfile;

import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassAccessFlagsList;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.enums.Version;
import com.wade.decompiler.repository.Repository;
import com.wade.decompiler.repository.SyntheticRepository;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
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
    private transient Repository repository = SyntheticRepository.getInstance();

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

    public String getClassName() {
        return constantPool.constantToString(getClassNameIndex(), ClassFileConstants.CONSTANT_Class);
    }

}
