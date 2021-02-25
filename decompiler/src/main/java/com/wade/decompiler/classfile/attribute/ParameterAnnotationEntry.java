package com.wade.decompiler.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.wade.decompiler.classfile.constant.ConstantPool;

public class ParameterAnnotationEntry {
    private AnnotationEntry[] annotationTable;

    public ParameterAnnotationEntry(DataInputStream input, ConstantPool constantPool) throws IOException {
        int annotation_table_length = input.readUnsignedShort();
        annotationTable = new AnnotationEntry[annotation_table_length];
        for (int i = 0; i < annotation_table_length; i++) {
            annotationTable[i] = AnnotationEntry.read(input, constantPool, false);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ParameterAnnotationEntry other = (ParameterAnnotationEntry) obj;
        if (!Arrays.equals(annotationTable, other.annotationTable))
            return false;
        return true;
    }

    public AnnotationEntry[] getAnnotationEntries() {
        return annotationTable;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(annotationTable);
        return result;
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
