package com.wade.app.attribute;

import java.io.DataInputStream;
import java.io.IOException;

public class BootstrapMethod {
    private int bootstrapMethodRef;
    private int[] bootstrapArguments;

    public BootstrapMethod(final BootstrapMethod c) {
        this(c.getBootstrapMethodRef(), c.getBootstrapArguments());
    }

    public BootstrapMethod(final DataInputStream input) throws IOException {
        this(input.readUnsignedShort(), input.readUnsignedShort());

        for (int i = 0; i < bootstrapArguments.length; i++) {
            bootstrapArguments[i] = input.readUnsignedShort();
        }
    }

    private BootstrapMethod(final int bootstrap_method_ref, final int num_bootstrap_arguments) {
        this(bootstrap_method_ref, new int[num_bootstrap_arguments]);
    }

    public BootstrapMethod(final int bootstrapMethodRef, final int[] bootstrapArguments) {
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
}
