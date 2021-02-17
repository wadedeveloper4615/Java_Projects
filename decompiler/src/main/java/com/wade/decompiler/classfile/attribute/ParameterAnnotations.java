package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.gen.Visitor;
import com.wade.decompiler.enums.ClassFileAttributes;

public abstract class ParameterAnnotations extends Attribute {
    private ParameterAnnotationEntry[] parameterAnnotationTable;

    public ParameterAnnotations(ClassFileAttributes parameter_annotation_type, int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(parameter_annotation_type, name_index, length, (ParameterAnnotationEntry[]) null, constant_pool);
        int num_parameters = input.readUnsignedByte();
        parameterAnnotationTable = new ParameterAnnotationEntry[num_parameters];
        for (int i = 0; i < num_parameters; i++) {
            parameterAnnotationTable[i] = new ParameterAnnotationEntry(input, constant_pool);
        }
    }

    public ParameterAnnotations(ClassFileAttributes parameterAnnotationType, int nameIndex, int length, ParameterAnnotationEntry[] parameterAnnotationTable, ConstantPool constantPool) {
        super(parameterAnnotationType, nameIndex, length, constantPool);
        this.parameterAnnotationTable = parameterAnnotationTable;
    }

    @Override
    public void accept(Visitor v) {
        v.visitParameterAnnotation(this);
    }

    @Override
    public Attribute copy(ConstantPool constant_pool) {
        return (Attribute) clone();
    }

    @Override
    public void dump(DataOutputStream dos) throws IOException {
        super.dump(dos);
        dos.writeByte(parameterAnnotationTable.length);
        for (ParameterAnnotationEntry element : parameterAnnotationTable) {
            element.dump(dos);
        }
    }

    public ParameterAnnotationEntry[] getParameterAnnotationEntries() {
        return parameterAnnotationTable;
    }

    public ParameterAnnotationEntry[] getParameterAnnotationTable() {
        return parameterAnnotationTable;
    }

    public void setParameterAnnotationTable(ParameterAnnotationEntry[] parameterAnnotationTable) {
        this.parameterAnnotationTable = parameterAnnotationTable;
    }
}
