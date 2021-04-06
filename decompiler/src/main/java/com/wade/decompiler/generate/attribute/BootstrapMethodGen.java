package com.wade.decompiler.generate.attribute;

import com.wade.decompiler.classfile.attribute.BootstrapMethod;
import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileConstants;

import java.util.Arrays;

public class BootstrapMethodGen {
    private String bootstrap_method_name;
    private int[] bootstrapArguments;

    public BootstrapMethodGen(BootstrapMethod attribute, ConstantPool constantPool) {
        bootstrap_method_name = constantPool.constantToString(attribute.getBootstrapMethodRef(), ClassFileConstants.CONSTANT_MethodHandle);
        bootstrapArguments = attribute.getBootstrapArguments();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        BootstrapMethodGen other = (BootstrapMethodGen) obj;
        if (!Arrays.equals(bootstrapArguments, other.bootstrapArguments)) return false;
        if (bootstrap_method_name == null) {
            if (other.bootstrap_method_name != null) return false;
        } else if (!bootstrap_method_name.equals(other.bootstrap_method_name)) return false;
        return true;
    }

    public String getBootstrap_method_name() {
        return bootstrap_method_name;
    }

    public int[] getBootstrapArguments() {
        return bootstrapArguments;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(bootstrapArguments);
        result = prime * result + ((bootstrap_method_name == null) ? 0 : bootstrap_method_name.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "BootstrapMethodGen [bootstrap_method_name=" + bootstrap_method_name + ", bootstrapArguments=" + Arrays.toString(bootstrapArguments) + "]";
    }
}
