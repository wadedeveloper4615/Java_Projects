package com.wade.decompiler.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.element.ElementValue;
import com.wade.decompiler.enums.ClassFileAttributes;

public class AnnotationDefault extends Attribute {
    private ElementValue defaultValue;

    public AnnotationDefault(int nameIndex, int length, DataInputStream input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, ElementValue.readElementValue(input, constantPool), constantPool);
    }

    public AnnotationDefault(int nameIndex, int length, ElementValue defaultValue, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_ANNOTATION_DEFAULT, nameIndex, length, constantPool);
        this.defaultValue = defaultValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        AnnotationDefault other = (AnnotationDefault) obj;
        if (defaultValue == null) {
            if (other.defaultValue != null)
                return false;
        } else if (!defaultValue.equals(other.defaultValue))
            return false;
        return true;
    }

    public ElementValue getDefaultValue() {
        return defaultValue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((defaultValue == null) ? 0 : defaultValue.hashCode());
        return result;
    }

    public void setDefaultValue(ElementValue defaultValue) {
        this.defaultValue = defaultValue;
    }
}
