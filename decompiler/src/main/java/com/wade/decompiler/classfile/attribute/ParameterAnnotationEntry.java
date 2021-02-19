package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wade.decompiler.classfile.constant.ConstantPool;

public class ParameterAnnotationEntry {
    private AnnotationEntry[] annotationTable;

    public ParameterAnnotationEntry(DataInput input, ConstantPool constant_pool) throws IOException {
        int annotation_table_length = input.readUnsignedShort();
        annotationTable = new AnnotationEntry[annotation_table_length];
        for (int i = 0; i < annotation_table_length; i++) {
            annotationTable[i] = AnnotationEntry.read(input, constant_pool, false);
        }
    }

    public AnnotationEntry[] getAnnotationEntries() {
        return annotationTable;
    }

    public static ParameterAnnotationEntry[] createParameterAnnotationEntries(Attribute[] attrs) {
        List<ParameterAnnotationEntry> accumulatedAnnotations = new ArrayList<>(attrs.length);
        for (Attribute attribute : attrs) {
            if (attribute instanceof ParameterAnnotations) {
                ParameterAnnotations runtimeAnnotations = (ParameterAnnotations) attribute;
                Collections.addAll(accumulatedAnnotations, runtimeAnnotations.getParameterAnnotationEntries());
            }
        }
        return accumulatedAnnotations.toArray(new ParameterAnnotationEntry[accumulatedAnnotations.size()]);
    }
}
