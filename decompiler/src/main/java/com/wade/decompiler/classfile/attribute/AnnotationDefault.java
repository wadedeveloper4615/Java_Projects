package com.wade.decompiler.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.element.ElementValue;
import com.wade.decompiler.enums.ClassFileAttributes;

public class AnnotationDefault extends Attribute {
    private ElementValue defaultValue;

    public AnnotationDefault(int name_index, int length, DataInputStream input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, ElementValue.readElementValue(input, constant_pool), constant_pool);
    }

    public AnnotationDefault(int name_index, int length, ElementValue defaultValue, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_ANNOTATION_DEFAULT, name_index, length, constant_pool);
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
