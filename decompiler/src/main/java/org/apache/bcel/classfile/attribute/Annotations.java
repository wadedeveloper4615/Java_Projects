
package org.apache.bcel.classfile.attribute;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.classfile.AnnotationEntry;
import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.classfile.constant.ConstantPool;

public abstract class Annotations extends Attribute {

    private AnnotationEntry[] annotationTable;
    private final boolean isRuntimeVisible;

    public Annotations(final byte annotationType, final int nameIndex, final int length, final AnnotationEntry[] annotationTable, final ConstantPool constantPool, final boolean isRuntimeVisible) {
        super(annotationType, nameIndex, length, constantPool);
        this.annotationTable = annotationTable;
        this.isRuntimeVisible = isRuntimeVisible;
    }

    protected Annotations(final byte annotation_type, final int name_index, final int length, final DataInput input, final ConstantPool constant_pool, final boolean isRuntimeVisible) throws IOException {
        this(annotation_type, name_index, length, (AnnotationEntry[]) null, constant_pool, isRuntimeVisible);
        final int annotation_table_length = input.readUnsignedShort();
        annotationTable = new AnnotationEntry[annotation_table_length];
        for (int i = 0; i < annotation_table_length; i++) {
            annotationTable[i] = AnnotationEntry.read(input, constant_pool, isRuntimeVisible);
        }
    }

    @Override
    public void accept(final Visitor v) {
        v.visitAnnotation(this);
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

    public final void setAnnotationTable(final AnnotationEntry[] annotationTable) {
        this.annotationTable = annotationTable;
    }

    protected void writeAnnotations(final DataOutputStream dos) throws IOException {
        if (annotationTable == null) {
            return;
        }
        dos.writeShort(annotationTable.length);
        for (final AnnotationEntry element : annotationTable) {
            element.dump(dos);
        }
    }
}
