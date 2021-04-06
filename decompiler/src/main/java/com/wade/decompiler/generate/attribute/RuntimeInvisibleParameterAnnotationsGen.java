package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.RuntimeInvisibleParameterAnnotations;
import com.wade.decompiler.classfile.constant.ConstantPool;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class RuntimeInvisibleParameterAnnotationsGen extends ParameterAnnotationsGen {
    public RuntimeInvisibleParameterAnnotationsGen(RuntimeInvisibleParameterAnnotations attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
    }
}
