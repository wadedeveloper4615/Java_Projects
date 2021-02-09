package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

public class AnnotationDefault extends Attribute {
    private ElementValue defaultValue;

    public AnnotationDefault(int name_index, int length, DataInputStream input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, (ElementValue) null, constant_pool);
        defaultValue = ElementValue.readElementValue(input, constant_pool);
    }

    public AnnotationDefault(int name_index, int length, ElementValue defaultValue, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_ANNOTATION_DEFAULT, name_index, length, constant_pool);
        this.defaultValue = defaultValue;
    }

    public ElementValue getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String toString() {
        return "AnnotationDefault [defaultValue=" + defaultValue + "]";
    }
}
