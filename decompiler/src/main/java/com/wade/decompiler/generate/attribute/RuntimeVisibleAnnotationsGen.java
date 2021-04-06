package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.RuntimeVisibleAnnotations;
import com.wade.decompiler.classfile.constant.ConstantPool;

public class RuntimeVisibleAnnotationsGen extends AnnotationsGen {
    public RuntimeVisibleAnnotationsGen(RuntimeVisibleAnnotations attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
    }

    @Override
    public String toString() {
        return "RuntimeVisibleAnnotationsGen [toString()=" + super.toString() + "]";
    }
}
