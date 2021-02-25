package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;

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

    public Annotations(ClassFileAttributes annotation_type, int nameIndex, int length, DataInput input, ConstantPool constantPool, boolean isRuntimeVisible) throws IOException {
        this(annotation_type, nameIndex, length, (AnnotationEntry[]) null, constantPool, isRuntimeVisible);
        int annotation_table_length = input.readUnsignedShort();
        annotationTable = new AnnotationEntry[annotation_table_length];
        for (int i = 0; i < annotation_table_length; i++) {
            annotationTable[i] = AnnotationEntry.read(input, constantPool, isRuntimeVisible);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Annotations other = (Annotations) obj;
        if (!Arrays.equals(annotationTable, other.annotationTable))
            return false;
        if (isRuntimeVisible != other.isRuntimeVisible)
            return false;
        return true;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(annotationTable);
        result = prime * result + (isRuntimeVisible ? 1231 : 1237);
        return result;
    }

    public boolean isRuntimeVisible() {
        return isRuntimeVisible;
    }

    @Override
    public String toString() {
        return "Annotations [annotationTable=" + Arrays.toString(annotationTable) + ", isRuntimeVisible=" + isRuntimeVisible + "]";
    }
}
