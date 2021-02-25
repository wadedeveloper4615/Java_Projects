package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.ClassFileAttributes;

public class BootstrapMethods extends Attribute {
    private BootstrapMethod[] bootstrapMethods;

    public BootstrapMethods(int nameIndex, int length, BootstrapMethod[] bootstrapMethods, ConstantPool constantPool) {
        super(ClassFileAttributes.ATTR_BOOTSTRAP_METHODS, nameIndex, length, constantPool);
        this.bootstrapMethods = bootstrapMethods;
    }

    public BootstrapMethods(int nameIndex, int length, DataInput input, ConstantPool constantPool) throws IOException {
        this(nameIndex, length, (BootstrapMethod[]) null, constantPool);
        int num_bootstrap_methods = input.readUnsignedShort();
        bootstrapMethods = new BootstrapMethod[num_bootstrap_methods];
        for (int i = 0; i < num_bootstrap_methods; i++) {
            bootstrapMethods[i] = new BootstrapMethod(input, constantPool);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        BootstrapMethods other = (BootstrapMethods) obj;
        if (!Arrays.equals(bootstrapMethods, other.bootstrapMethods))
            return false;
        return true;
    }

    public BootstrapMethod[] getBootstrapMethods() {
        return bootstrapMethods;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(bootstrapMethods);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("BootstrapMethods(");
        buf.append(bootstrapMethods.length);
        buf.append("):");
        for (int i = 0; i < bootstrapMethods.length; i++) {
            buf.append("\n");
            int start = buf.length();
            buf.append("  ").append(i).append(": ");
            int indent_count = buf.length() - start;
            String[] lines = (bootstrapMethods[i].toString(super.getConstantPool())).split("\\r?\\n");
            buf.append(lines[0]);
            for (int j = 1; j < lines.length; j++) {
                buf.append("\n").append("          ".substring(0, indent_count)).append(lines[j]);
            }
        }
        return buf.toString();
    }
}
