package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

public abstract class Annotations extends Attribute {
    private AnnotationEntry[] annotationTable;
    private boolean isRuntimeVisible;

    public Annotations(ClassFileAttributes annotationType, int nameIndex, int length, AnnotationEntry[] annotationTable, ConstantPool constantPool, boolean isRuntimeVisible) {
        super(annotationType, nameIndex, length, constantPool);
        this.annotationTable = annotationTable;
        this.isRuntimeVisible = isRuntimeVisible;
    }

    public Annotations(ClassFileAttributes annotation_type, int name_index, int length, DataInputStream input, ConstantPool constant_pool, boolean isRuntimeVisible) throws IOException {
        this(annotation_type, name_index, length, (AnnotationEntry[]) null, constant_pool, isRuntimeVisible);
        int annotation_table_length = input.readUnsignedShort();
        annotationTable = new AnnotationEntry[annotation_table_length];
        for (int i = 0; i < annotation_table_length; i++) {
            annotationTable[i] = AnnotationEntry.read(input, constant_pool, isRuntimeVisible);
        }
    }

    public AnnotationEntry[] getAnnotationTable() {
        return annotationTable;
    }

    public boolean isRuntimeVisible() {
        return isRuntimeVisible;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
