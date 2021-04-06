package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.RuntimeVisibleParameterAnnotations;
import com.wade.decompiler.classfile.constant.ConstantPool;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class RuntimeVisibleParameterAnnotationsGen extends ParameterAnnotationsGen {
    public RuntimeVisibleParameterAnnotationsGen(RuntimeVisibleParameterAnnotations attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
    }
}
