package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.wade.app.constantpool.ConstantPool;

public class ParameterAnnotationEntry {
    private AnnotationEntry[] annotationTable;

    public ParameterAnnotationEntry(DataInputStream input, ConstantPool constant_pool) throws IOException {
        int annotation_table_length = input.readUnsignedShort();
        annotationTable = new AnnotationEntry[annotation_table_length];
        for (int i = 0; i < annotation_table_length; i++) {
            // TODO isRuntimeVisible
            annotationTable[i] = AnnotationEntry.read(input, constant_pool, false);
        }
    }

    @Override
    public String toString() {
        return "ParameterAnnotationEntry [annotationTable=" + Arrays.toString(annotationTable) + "]";
    }

}
