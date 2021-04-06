package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.util.Utility;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true, includeFieldNames = true)
@EqualsAndHashCode
public class BootstrapMethod {
    private int bootstrapMethodRef;
    private List<Integer> bootstrapArguments;
    private String bootstrap_method_name;

    public BootstrapMethod(DataInput input, ConstantPool constantPool) throws IOException {
        this(input.readUnsignedShort(), input.readUnsignedShort(), constantPool);
        for (int i = 0; i < bootstrapArguments.size(); i++) {
            bootstrapArguments.add(input.readUnsignedShort());
        }
    }

    private BootstrapMethod(int bootstrap_method_ref, int num_bootstrap_arguments, ConstantPool constantPool) {
        this(bootstrap_method_ref, new ArrayList<Integer>());
        bootstrap_method_name = constantPool.constantToString(bootstrapMethodRef, ClassFileConstants.CONSTANT_MethodHandle);
    }

    public BootstrapMethod(int bootstrapMethodRef, List<Integer> bootstrapArguments) {
        this.bootstrapMethodRef = bootstrapMethodRef;
        this.bootstrapArguments = bootstrapArguments;
    }
}
