package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

import com.wade.app.Const;
import com.wade.app.constantpool.ConstantPool;

public class BootstrapMethods extends Attribute {
    private BootstrapMethod[] bootstrapMethods;

    public BootstrapMethods(final BootstrapMethods c) {
        this(c.getNameIndex(), c.getLength(), c.getBootstrapMethods(), c.getConstantPool());
    }

    public BootstrapMethods(final int name_index, final int length, final BootstrapMethod[] bootstrapMethods, final ConstantPool constant_pool) {
        super(Const.ATTR_BOOTSTRAP_METHODS, name_index, length, constant_pool);
        this.bootstrapMethods = bootstrapMethods;
    }

    public BootstrapMethods(final int name_index, final int length, final DataInputStream input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, (BootstrapMethod[]) null, constant_pool);

        final int num_bootstrap_methods = input.readUnsignedShort();
        bootstrapMethods = new BootstrapMethod[num_bootstrap_methods];
        for (int i = 0; i < num_bootstrap_methods; i++) {
            bootstrapMethods[i] = new BootstrapMethod(input);
        }
    }

    public final BootstrapMethod[] getBootstrapMethods() {
        return bootstrapMethods;
    }

    public final void setBootstrapMethods(final BootstrapMethod[] bootstrapMethods) {
        this.bootstrapMethods = bootstrapMethods;
    }
}
