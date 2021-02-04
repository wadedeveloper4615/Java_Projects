package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.constantpool.ConstantPool;

public abstract class ParameterAnnotations extends Attribute {
    private ParameterAnnotationEntry[] parameterAnnotationTable;

    ParameterAnnotations(final byte parameter_annotation_type, final int name_index, final int length, final DataInputStream input, final ConstantPool constant_pool) throws IOException {
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

    public ParameterAnnotationEntry[] getParameterAnnotationEntries() {
        return parameterAnnotationTable;
    }

    public final ParameterAnnotationEntry[] getParameterAnnotationTable() {
        return parameterAnnotationTable;
    }
}
