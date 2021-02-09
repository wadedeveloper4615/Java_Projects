package com.wade.app.classfile.attribute;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.wade.app.constantpool.ConstantPool;
import com.wade.app.enums.ClassFileAttributes;

public class BootstrapMethods extends Attribute {
    private BootstrapMethod[] bootstrapMethods;

    public BootstrapMethods(int name_index, int length, BootstrapMethod[] bootstrapMethods, ConstantPool constant_pool) {
        super(ClassFileAttributes.ATTR_BOOTSTRAP_METHODS, name_index, length, constant_pool);
        this.bootstrapMethods = bootstrapMethods;
    }

    public BootstrapMethods(int name_index, int length, DataInputStream input, ConstantPool constant_pool) throws IOException {
        this(name_index, length, (BootstrapMethod[]) null, constant_pool);

        int num_bootstrap_methods = input.readUnsignedShort();
        bootstrapMethods = new BootstrapMethod[num_bootstrap_methods];
        for (int i = 0; i < num_bootstrap_methods; i++) {
            bootstrapMethods[i] = new BootstrapMethod(input);
        }
    }

    @Override
    public String toString() {
        return "BootstrapMethods [bootstrapMethods=" + Arrays.toString(bootstrapMethods) + "]";
    }
}
