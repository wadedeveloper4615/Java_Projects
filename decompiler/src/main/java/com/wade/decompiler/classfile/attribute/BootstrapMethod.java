package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.util.Utility;

public class BootstrapMethod {
    private int bootstrapMethodRef;
    private int[] bootstrapArguments;
    private String bootstrap_method_name;

    public BootstrapMethod(DataInput input, ConstantPool constantPool) throws IOException {
        this(input.readUnsignedShort(), input.readUnsignedShort(), constantPool);
        for (int i = 0; i < bootstrapArguments.length; i++) {
            bootstrapArguments[i] = input.readUnsignedShort();
        }
    }

    private BootstrapMethod(int bootstrap_method_ref, int num_bootstrap_arguments, ConstantPool constantPool) {
        this(bootstrap_method_ref, new int[num_bootstrap_arguments]);
        bootstrap_method_name = constantPool.constantToString(bootstrapMethodRef, ClassFileConstants.CONSTANT_MethodHandle);
    }

    public BootstrapMethod(int bootstrapMethodRef, int[] bootstrapArguments) {
        this.bootstrapMethodRef = bootstrapMethodRef;
        this.bootstrapArguments = bootstrapArguments;
    }

    public int[] getBootstrapArguments() {
        return bootstrapArguments;
    }

    public int getBootstrapMethodRef() {
        return bootstrapMethodRef;
    }

    public int getNumBootstrapArguments() {
        return bootstrapArguments.length;
    }

    @Override
    public String toString() {
        return "BootstrapMethod(" + bootstrapMethodRef + ", " + bootstrapArguments.length + ", " + Arrays.toString(bootstrapArguments) + ")";
    }

    public String toString(ConstantPool constantPool) {
        StringBuilder buf = new StringBuilder();
        buf.append(Utility.compactClassName(bootstrap_method_name, false));
        int num_bootstrap_arguments = bootstrapArguments.length;
        if (num_bootstrap_arguments > 0) {
            buf.append("\nMethod Arguments:");
            for (int i = 0; i < num_bootstrap_arguments; i++) {
                buf.append("\n  ").append(i).append(": ");
                buf.append(constantPool.constantToString(constantPool.getConstant(bootstrapArguments[i])));
            }
        }
        return buf.toString();
    }
}
