package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileAttributes;

public class AnnotationDefault extends Attribute {
    private ElementValue defaultValue;

    public AnnotationDefault(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, (ElementValue) null, constant_pool);
        defaultValue = ElementValue.readElementValue(input, constant_pool);
    }

    public AnnotationDefault(int name_index, int length, ElementValue defaultValue, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_ANNOTATION_DEFAULT, name_index, length, constant_pool);
        this.defaultValue = defaultValue;
    }

    @Override
    public void accept(Visitor v) {
        v.visitAnnotationDefault(this);
    }

    @Override
    public Attribute copy(ConstantPool _constant_pool) {
        return (Attribute) clone();
    }

    @Override
    public void dump(DataOutputStream dos) throws IOException {
        super.dump(dos);
        defaultValue.dump(dos);
    }

    public ElementValue getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(ElementValue defaultValue) {
        this.defaultValue = defaultValue;
    }
}
