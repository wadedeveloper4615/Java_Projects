package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.constantpool.ConstantPool;

public abstract class Annotations extends Attribute {
    private AnnotationEntry[] annotationTable;
    private final boolean isRuntimeVisible;

    public Annotations(final byte annotationType, final int nameIndex, final int length, final AnnotationEntry[] annotationTable, final ConstantPool constantPool, final boolean isRuntimeVisible) {
        super(annotationType, nameIndex, length, constantPool);
        this.annotationTable = annotationTable;
        this.isRuntimeVisible = isRuntimeVisible;
    }

    public Annotations(final byte annotation_type, final int name_index, final int length, final DataInputStream input, final ConstantPool constant_pool, final boolean isRuntimeVisible) throws IOException {
        this(annotation_type, name_index, length, (AnnotationEntry[]) null, constant_pool, isRuntimeVisible);
        final int annotation_table_length = input.readUnsignedShort();
        annotationTable = new AnnotationEntry[annotation_table_length];
        for (int i = 0; i < annotation_table_length; i++) {
            annotationTable[i] = AnnotationEntry.read(input, constant_pool, isRuntimeVisible);
        }
    }

    public AnnotationEntry[] getAnnotationEntries() {
        return annotationTable;
    }

    public final int getNumAnnotations() {
        if (annotationTable == null) {
            return 0;
        }
        return annotationTable.length;
    }

    public boolean isRuntimeVisible() {
        return isRuntimeVisible;
    }
}
