package com.wade.decompiler.generate.attribute;

import java.util.Arrays;

import com.wade.decompiler.classfile.attribute.MethodParameter;
import com.wade.decompiler.classfile.attribute.MethodParameters;
import com.wade.decompiler.classfile.constant.ConstantPool;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MethodParametersGen other = (MethodParametersGen) obj;
        if (!Arrays.equals(parameters, other.parameters))
            return false;
        return true;
    }

    public MethodParameterGen[] getParameters() {
        return parameters;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(parameters);
        return result;
    }

    @Override
    public String toString() {
        return "MethodParametersGen [parameters=" + Arrays.toString(parameters) + "]";
    }
}
