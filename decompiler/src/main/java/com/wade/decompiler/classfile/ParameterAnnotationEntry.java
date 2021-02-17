package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParameterAnnotationEntry implements Node {
    private AnnotationEntry[] annotationTable;

    ParameterAnnotationEntry(DataInput input, ConstantPool constant_pool) throws IOException {
        int annotation_table_length = input.readUnsignedShort();
        annotationTable = new AnnotationEntry[annotation_table_length];
        for (int i = 0; i < annotation_table_length; i++) {
            // TODO isRuntimeVisible
            annotationTable[i] = AnnotationEntry.read(input, constant_pool, false);
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visitParameterAnnotationEntry(this);
    }

    public void dump(DataOutputStream dos) throws IOException {
        dos.writeShort(annotationTable.length);
        for (AnnotationEntry entry : annotationTable) {
            entry.dump(dos);
        }
    }

    public AnnotationEntry[] getAnnotationEntries() {
        return annotationTable;
    }

    public static ParameterAnnotationEntry[] createParameterAnnotationEntries(Attribute[] attrs) {
        // Find attributes that contain parameter annotation data
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
