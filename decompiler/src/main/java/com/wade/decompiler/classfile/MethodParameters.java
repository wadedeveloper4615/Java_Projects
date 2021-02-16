package com.wade.decompiler.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.ClassFileAttributes;

public class MethodParameters extends Attribute {
    private MethodParameter[] parameters = new MethodParameter[0];

    public MethodParameters(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        super(ClassFileAttributes.ATTR_METHOD_PARAMETERS, name_index, length, constant_pool);
        int parameters_count = input.readUnsignedByte();
        parameters = new MethodParameter[parameters_count];
        for (int i = 0; i < parameters_count; i++) {
            parameters[i] = new MethodParameter(input);
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visitMethodParameters(this);
    }

    @Override
    public Attribute copy(ConstantPool _constant_pool) {
        MethodParameters c = (MethodParameters) clone();
        c.parameters = new MethodParameter[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            c.parameters[i] = parameters[i].copy();
        }
        c.setConstantPool(_constant_pool);
        return c;
    }

    @Override
    public void dump(DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeByte(parameters.length);
        for (MethodParameter parameter : parameters) {
            parameter.dump(file);
        }
    }

    public MethodParameter[] getParameters() {
        return parameters;
    }

    public void setParameters(MethodParameter[] parameters) {
        this.parameters = parameters;
    }
}
