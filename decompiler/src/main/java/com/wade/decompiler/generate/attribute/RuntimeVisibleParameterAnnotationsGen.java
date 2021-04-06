package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.RuntimeVisibleParameterAnnotations;
import com.wade.decompiler.classfile.constant.ConstantPool;

public class RuntimeVisibleParameterAnnotationsGen extends ParameterAnnotationsGen {
    public RuntimeVisibleParameterAnnotationsGen(RuntimeVisibleParameterAnnotations attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
