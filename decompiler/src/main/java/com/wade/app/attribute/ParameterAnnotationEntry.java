package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wade.app.constantpool.ConstantPool;
import com.wade.app.constantpool.Node;

public class ParameterAnnotationEntry implements Node {
    private final AnnotationEntry[] annotationTable;

    ParameterAnnotationEntry(final DataInputStream input, final ConstantPool constant_pool) throws IOException {
        final int annotation_table_length = input.readUnsignedShort();
        annotationTable = new AnnotationEntry[annotation_table_length];
        for (int i = 0; i < annotation_table_length; i++) {
            annotationTable[i] = AnnotationEntry.read(input, constant_pool, false);
        }
    }

    public static ParameterAnnotationEntry[] createParameterAnnotationEntries(final Attribute[] attrs) {
        // Find attributes that contain parameter annotation data
        final List<ParameterAnnotationEntry> accumulatedAnnotations = new ArrayList<>(attrs.length);
        for (final Attribute attribute : attrs) {
            if (attribute instanceof ParameterAnnotations) {
                final ParameterAnnotations runtimeAnnotations = (ParameterAnnotations) attribute;
                Collections.addAll(accumulatedAnnotations, runtimeAnnotations.getParameterAnnotationEntries());
            }
        }
        return accumulatedAnnotations.toArray(new ParameterAnnotationEntry[accumulatedAnnotations.size()]);
    }
}
