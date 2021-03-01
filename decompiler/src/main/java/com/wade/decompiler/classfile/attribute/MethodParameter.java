package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassAccessFlags;
import com.wade.decompiler.enums.ClassFileConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class MethodParameter {
    private int nameIndex;
    private ClassAccessFlags accessFlags;
    private String name;

    public MethodParameter(DataInput input, ConstantPool constantPool) throws IOException {
        nameIndex = input.readUnsignedShort();
        accessFlags = ClassAccessFlags.read(input.readUnsignedShort());
        name = ((ConstantUtf8) constantPool.getConstant(nameIndex, ClassFileConstants.CONSTANT_Utf8)).getBytes();
    }
}
