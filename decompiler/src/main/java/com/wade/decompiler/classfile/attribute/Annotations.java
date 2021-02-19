package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public abstract class Annotations extends Attribute {
    private AnnotationEntry[] annotationTable;
    private boolean isRuntimeVisible;

    public Annotations(ClassFileAttributes annotationType, int nameIndex, int length, AnnotationEntry[] annotationTable, ConstantPool constantPool, boolean isRuntimeVisible) {
        super(annotationType, nameIndex, length, constantPool);
        this.annotationTable = annotationTable;
        this.isRuntimeVisible = isRuntimeVisible;
    }

    public Annotations(ClassFileAttributes annotation_type, int name_index, int length, DataInput input, ConstantPool constant_pool, boolean isRuntimeVisible) throws IOException {
        this(annotation_type, name_index, length, (AnnotationEntry[]) null, constant_pool, isRuntimeVisible);
        int annotation_table_length = input.readUnsignedShort();
        annotationTable = new AnnotationEntry[annotation_table_length];
        for (int i = 0; i < annotation_table_length; i++) {
            annotationTable[i] = AnnotationEntry.read(input, constant_pool, isRuntimeVisible);
        }
    }

    public AnnotationEntry[] getAnnotationEntries() {
        return annotationTable;
    }

    public int getNumAnnotations() {
        if (annotationTable == null) {
            return 0;
        }
        return annotationTable.length;
    }

    public boolean isRuntimeVisible() {
        return isRuntimeVisible;
    }
}