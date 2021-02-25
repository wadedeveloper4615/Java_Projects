package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class MethodParameters extends Attribute {
    private MethodParameter[] parameters = new MethodParameter[0];

    public MethodParameters(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        super(ClassFileAttributes.ATTR_METHOD_PARAMETERS, nameIndex, length, constantPool);
        int parameters_count = input.readUnsignedByte();
        parameters = new MethodParameter[parameters_count];
        for (int i = 0; i < parameters_count; i++) {
            parameters[i] = new MethodParameter(input, constantPool);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        MethodParameters other = (MethodParameters) obj;
        if (!Arrays.equals(parameters, other.parameters))
            return false;
        return true;
    }

    public MethodParameter[] getParameters() {
        return parameters;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(parameters);
        return result;
    }

    @Override
    public String toString() {
        return "MethodParameters [parameters=" + Arrays.toString(parameters) + "]";
    }
}
