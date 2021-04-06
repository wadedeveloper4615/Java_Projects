package com.wade.decompiler.generate.attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private List<MethodParameterGen> parameters;

    public MethodParametersGen(MethodParameters attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        this.parameters = new ArrayList<>();
        for (MethodParameter entry : attribute.getParameters()) {
            this.parameters.add(new MethodParameterGen(entry, constantPool));
        }
    }
}
