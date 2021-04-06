package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class MethodParameters extends Attribute {
    private List<MethodParameter> parameters;

    public MethodParameters(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        super(ClassFileAttributes.ATTR_METHOD_PARAMETERS, nameIndex, length, constantPool);
        int parameters_count = input.readUnsignedByte();
        parameters = new ArrayList<>();
        for (int i = 0; i < parameters_count; i++) {
            parameters.add(new MethodParameter(input, constantPool));
        }
    }
}
