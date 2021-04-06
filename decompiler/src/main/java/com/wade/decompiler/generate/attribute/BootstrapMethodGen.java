package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.BootstrapMethod;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode(callSuper = false)
public class BootstrapMethodGen {
    private String bootstrap_method_name;
    private int[] bootstrapArguments;

    public BootstrapMethodGen(BootstrapMethod attribute, ConstantPool constantPool) {
        bootstrap_method_name = constantPool.constantToString(attribute.getBootstrapMethodRef(), ClassFileConstants.CONSTANT_MethodHandle);
        bootstrapArguments = attribute.getBootstrapArguments();
    }
}
