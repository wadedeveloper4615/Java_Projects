package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.RuntimeInvisibleParameterAnnotations;
import com.wade.decompiler.classfile.constant.ConstantPool;

public class RuntimeInvisibleParameterAnnotationsGen extends ParameterAnnotationsGen {
    public RuntimeInvisibleParameterAnnotationsGen(RuntimeInvisibleParameterAnnotations attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
    }
}
