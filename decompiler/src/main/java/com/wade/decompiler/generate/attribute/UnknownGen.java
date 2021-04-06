package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.constant.ConstantPool;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class UnknownGen extends AttributeGen {
    public UnknownGen(Attribute attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
    }
}
