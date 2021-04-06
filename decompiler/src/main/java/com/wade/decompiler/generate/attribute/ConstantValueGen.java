package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.ConstantValue;
import com.wade.decompiler.classfile.constant.Constant;
import com.wade.decompiler.classfile.constant.ConstantPool;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class ConstantValueGen extends AttributeGen {
    private Constant value;

    public ConstantValueGen(ConstantValue attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        value = constantPool.getConstant(attribute.getConstantValueIndex());
    }
}
