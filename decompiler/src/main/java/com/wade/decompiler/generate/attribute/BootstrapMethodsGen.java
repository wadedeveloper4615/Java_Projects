package com.wade.decompiler.generate.attribute;

import java.util.Arrays;

import com.wade.decompiler.classfile.attribute.BootstrapMethod;
import com.wade.decompiler.classfile.attribute.BootstrapMethods;
import com.wade.decompiler.classfile.constant.ConstantPool;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        BootstrapMethodsGen other = (BootstrapMethodsGen) obj;
        if (!Arrays.equals(bootstrapMethods, other.bootstrapMethods)) return false;
        return true;
    }

    public BootstrapMethodGen[] getBootstrapMethods() {
        return bootstrapMethods;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(bootstrapMethods);
        return result;
    }

    @Override
    public String toString() {
        return "BootstrapMethodsGen [bootstrapMethods=" + Arrays.toString(bootstrapMethods) + "]";
    }
}
