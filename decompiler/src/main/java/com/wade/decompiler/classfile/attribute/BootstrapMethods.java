package com.wade.decompiler.classfile.attribute;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.gen.Visitor;
import com.wade.decompiler.enums.ClassFileAttributes;

public class BootstrapMethods extends Attribute {
    private BootstrapMethod[] bootstrapMethods;

    public BootstrapMethods(BootstrapMethods c) {
        this(c.getNameIndex(), c.getLength(), c.getBootstrapMethods(), c.getConstantPool());
    }

    public BootstrapMethods(int name_index, int length, BootstrapMethod[] bootstrapMethods, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_BOOTSTRAP_METHODS, name_index, length, constant_pool);
        this.bootstrapMethods = bootstrapMethods;
    }

    public BootstrapMethods(int name_index, int length, DataInput input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, (BootstrapMethod[]) null, constant_pool);
        int num_bootstrap_methods = input.readUnsignedShort();
        bootstrapMethods = new BootstrapMethod[num_bootstrap_methods];
        for (int i = 0; i < num_bootstrap_methods; i++) {
            bootstrapMethods[i] = new BootstrapMethod(input);
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visitBootstrapMethods(this);
    }

    @Override
    public BootstrapMethods copy(ConstantPool _constant_pool) {
        BootstrapMethods c = (BootstrapMethods) clone();
        c.bootstrapMethods = new BootstrapMethod[bootstrapMethods.length];
        for (int i = 0; i < bootstrapMethods.length; i++) {
            c.bootstrapMethods[i] = bootstrapMethods[i].copy();
        }
        c.setConstantPool(_constant_pool);
        return c;
    }

    @Override
    public void dump(DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(bootstrapMethods.length);
        for (BootstrapMethod bootstrap_method : bootstrapMethods) {
            bootstrap_method.dump(file);
        }
    }

    public BootstrapMethod[] getBootstrapMethods() {
        return bootstrapMethods;
    }

    public void setBootstrapMethods(BootstrapMethod[] bootstrapMethods) {
        this.bootstrapMethods = bootstrapMethods;
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
