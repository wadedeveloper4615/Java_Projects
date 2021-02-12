
package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.bcel.classfile.attribute.Attribute;
import org.apache.bcel.classfile.attribute.ParameterAnnotations;
import org.apache.bcel.classfile.constant.ConstantPool;

public class ParameterAnnotationEntry implements Node {

    private final AnnotationEntry[] annotationTable;

    public ParameterAnnotationEntry(final DataInput input, final ConstantPool constant_pool) throws IOException {
        final int annotation_table_length = input.readUnsignedShort();
        annotationTable = new AnnotationEntry[annotation_table_length];
        for (int i = 0; i < annotation_table_length; i++) {
            // TODO isRuntimeVisible
            annotationTable[i] = AnnotationEntry.read(input, constant_pool, false);
        }
    }

    @Override
    public void accept(final Visitor v) {
        v.visitParameterAnnotationEntry(this);
    }

    public void dump(final DataOutputStream dos) throws IOException {
        dos.writeShort(annotationTable.length);
        for (final AnnotationEntry entry : annotationTable) {
            entry.dump(dos);
        }
    }

    public AnnotationEntry[] getAnnotationEntries() {
        return annotationTable;
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
