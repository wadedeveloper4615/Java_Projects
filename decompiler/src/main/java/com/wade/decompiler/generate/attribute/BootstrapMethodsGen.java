package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.BootstrapMethod;
import com.wade.decompiler.classfile.attribute.BootstrapMethods;
import com.wade.decompiler.classfile.constant.ConstantPool;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class BootstrapMethodsGen extends AttributeGen {
    private BootstrapMethodGen[] bootstrapMethods;

    public BootstrapMethodsGen(BootstrapMethods attribute, ConstantPool constantPool) {
        super(attribute, constantPool);
        BootstrapMethod[] bootstrapMethods = attribute.getBootstrapMethods();
        int num_bootstrap_methods = bootstrapMethods.length;
        this.bootstrapMethods = new BootstrapMethodGen[num_bootstrap_methods];
        for (int i = 0; i < num_bootstrap_methods; i++) {
            this.bootstrapMethods[i] = new BootstrapMethodGen(bootstrapMethods[i], constantPool);
        }
    }
}
