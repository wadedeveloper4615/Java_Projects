package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.LocalVariable;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.constant.ConstantUtf8;
import com.wade.decompiler.enums.ClassFileConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode
public class LocalVariableGen {
    private int startPc;
    private int length;
    private String name;
    private String signature;
    private int index;
    private int origIndex;

    public LocalVariableGen(LocalVariable attribute, ConstantPool constantPool) {
        this.startPc = attribute.getStartPc();
        this.length = attribute.getLength();
        this.name = ((ConstantUtf8) constantPool.getConstant(attribute.getNameIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        this.signature = ((ConstantUtf8) constantPool.getConstant(attribute.getSignatureIndex(), ClassFileConstants.CONSTANT_Utf8)).getBytes();
        this.index = attribute.getIndex();
        this.origIndex = attribute.getIndex();
    }
}
