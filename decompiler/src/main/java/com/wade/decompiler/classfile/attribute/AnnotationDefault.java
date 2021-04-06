package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.element.ElementValue;
import com.wade.decompiler.enums.ClassFileAttributes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = true)
public class AnnotationDefault extends Attribute {
    private ElementValue defaultValue;

    public AnnotationDefault(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, ElementValue.readElementValue(input, constantPool), constantPool);
    }

    public AnnotationDefault(int nameIndex, int length, ElementValue defaultValue, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_ANNOTATION_DEFAULT, nameIndex, length, constantPool);
        this.defaultValue = defaultValue;
    }
}
