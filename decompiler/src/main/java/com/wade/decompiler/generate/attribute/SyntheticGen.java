package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.Synthetic;
import com.wade.decompiler.classfile.constant.ConstantPool;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class SyntheticGen extends AttributeGen {
    private byte[] bytes;

    public SyntheticGen(Synthetic attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        this.bytes = attribute.getBytes();
    }
}
