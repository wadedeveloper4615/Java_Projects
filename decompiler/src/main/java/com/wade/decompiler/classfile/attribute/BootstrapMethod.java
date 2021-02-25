package com.wade.decompiler.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;
import com.wade.decompiler.util.Utility;

public class BootstrapMethod {
    private int bootstrapMethodRef;
    private int[] bootstrapArguments;
    private String bootstrap_method_name;

    public BootstrapMethod(DataInputStream input, ConstantPool constantPool) throws IOException {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BootstrapMethod other = (BootstrapMethod) obj;
        if (!Arrays.equals(bootstrapArguments, other.bootstrapArguments))
            return false;
        if (bootstrapMethodRef != other.bootstrapMethodRef)
            return false;
        if (bootstrap_method_name == null) {
            if (other.bootstrap_method_name != null)
                return false;
        } else if (!bootstrap_method_name.equals(other.bootstrap_method_name))
            return false;
        return true;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(bootstrapArguments);
        result = prime * result + bootstrapMethodRef;
        result = prime * result + ((bootstrap_method_name == null) ? 0 : bootstrap_method_name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "BootstrapMethod [bootstrapMethodRef=" + bootstrapMethodRef + ", bootstrapArguments=" + Arrays.toString(bootstrapArguments) + "]";
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
