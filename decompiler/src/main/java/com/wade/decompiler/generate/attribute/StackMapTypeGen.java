package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.StackMapType;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class StackMapTypeGen {
    private byte type;
    private String name;

    public StackMapTypeGen(StackMapType attribute, ConstantPool constantPool) {
        this.type = attribute.getType();
        this.name = constantPool.constantToString(attribute.getIndex(), ClassFileConstants.CONSTANT_Class);
    }
}
