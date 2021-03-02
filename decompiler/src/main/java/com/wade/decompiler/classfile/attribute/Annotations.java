package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public abstract class Annotations extends Attribute {
    private AnnotationEntry[] annotationTable;
    private boolean isRuntimeVisible;

    public Annotations(ClassFileAttributes annotationType, int nameIndex, int length, AnnotationEntry[] annotationTable, ConstantPool constantPool, boolean isRuntimeVisible) {
        super(annotationType, nameIndex, length, constantPool);
        this.annotationTable = annotationTable;
        this.isRuntimeVisible = isRuntimeVisible;
    }

    public Annotations(ClassFileAttributes annotation_type, int nameIndex, int length, DataInput input, ConstantPool constantPool, boolean isRuntimeVisible) throws IOException {
        this(annotation_type, nameIndex, length, (AnnotationEntry[]) null, constantPool, isRuntimeVisible);
        int annotation_table_length = input.readUnsignedShort();
        annotationTable = new AnnotationEntry[annotation_table_length];
        for (int i = 0; i < annotation_table_length; i++) {
            annotationTable[i] = AnnotationEntry.read(input, constantPool, isRuntimeVisible);
        }
    }

}
