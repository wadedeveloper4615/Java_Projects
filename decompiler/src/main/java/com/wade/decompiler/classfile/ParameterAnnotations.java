package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class ParameterAnnotations extends Attribute {
    private ParameterAnnotationEntry[] parameterAnnotationTable;

    ParameterAnnotations(final byte parameter_annotation_type, final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        this(parameter_annotation_type, name_index, length, (ParameterAnnotationEntry[]) null, constant_pool);
        final int num_parameters = input.readUnsignedByte();
        parameterAnnotationTable = new ParameterAnnotationEntry[num_parameters];
        for (int i = 0; i < num_parameters; i++) {
            parameterAnnotationTable[i] = new ParameterAnnotationEntry(input, constant_pool);
        }
    }

    public ParameterAnnotations(final byte parameterAnnotationType, final int nameIndex, final int length, final ParameterAnnotationEntry[] parameterAnnotationTable, final ConstantPool constantPool) {
        super(parameterAnnotationType, nameIndex, length, constantPool);
        this.parameterAnnotationTable = parameterAnnotationTable;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitParameterAnnotation(this);
    }

    @Override
    public Attribute copy(final ConstantPool constant_pool) {
        return (Attribute) clone();
    }

    @Override
    public void dump(final DataOutputStream dos) throws IOException {
        super.dump(dos);
        dos.writeByte(parameterAnnotationTable.length);
        for (final ParameterAnnotationEntry element : parameterAnnotationTable) {
            element.dump(dos);
        }
    }

    public ParameterAnnotationEntry[] getParameterAnnotationEntries() {
        return parameterAnnotationTable;
    }

    public final ParameterAnnotationEntry[] getParameterAnnotationTable() {
        return parameterAnnotationTable;
    }

    public final void setParameterAnnotationTable(final ParameterAnnotationEntry[] parameterAnnotationTable) {
        this.parameterAnnotationTable = parameterAnnotationTable;
    }
}
