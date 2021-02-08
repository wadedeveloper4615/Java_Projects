package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

public abstract class ParameterAnnotations extends Attribute {
    private ParameterAnnotationEntry[] parameterAnnotationTable;

    public ParameterAnnotations(ClassFileAttributes parameter_annotation_type,  int name_index,  int length,  DataInputStream input,  ConstantPool constant_pool) throws IOException {
        this(parameter_annotation_type, name_index, length, (ParameterAnnotationEntry[]) null, constant_pool);
         int num_parameters = input.readUnsignedByte();
        parameterAnnotationTable = new ParameterAnnotationEntry[num_parameters];
        for (int i = 0; i < num_parameters; i++) {
            parameterAnnotationTable[i] = new ParameterAnnotationEntry(input, constant_pool);
        }
    }

    public ParameterAnnotations(ClassFileAttributes parameterAnnotationType,  int nameIndex,  int length,  ParameterAnnotationEntry[] parameterAnnotationTable,  ConstantPool constantPool) {
        super(parameterAnnotationType, nameIndex, length, constantPool);
        this.parameterAnnotationTable = parameterAnnotationTable;
    }

}
