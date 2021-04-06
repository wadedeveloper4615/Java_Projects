package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.MethodParameter;
import com.wade.decompiler.classfile.attribute.MethodParameters;
import com.wade.decompiler.classfile.constant.ConstantPool;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class MethodParametersGen extends AttributeGen {
    private MethodParameterGen[] parameters;

    public MethodParametersGen(MethodParameters attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        MethodParameter[] parameters = attribute.getParameters();
        int parameters_count = parameters.length;
        this.parameters = new MethodParameterGen[parameters_count];
        for (int i = 0; i < parameters_count; i++) {
            this.parameters[i] = new MethodParameterGen(parameters[i], constantPool);
        }
    }
}
