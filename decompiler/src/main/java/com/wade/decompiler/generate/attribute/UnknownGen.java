package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.Attribute;
import com.wade.decompiler.classfile.constant.ConstantPool;

public class UnknownGen extends AttributeGen {
    public UnknownGen(Attribute attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
    }
}
