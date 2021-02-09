package com.wade.app.classfile.attribute;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;

public class BootstrapMethod implements Cloneable {
    private int bootstrapMethodRef;
    private int[] bootstrapArguments;

    public BootstrapMethod(DataInput input) throws IOException {
        this(input.readUnsignedShort(), input.readUnsignedShort());
        for (int i = 0; i < bootstrapArguments.length; i++) {
            bootstrapArguments[i] = input.readUnsignedShort();
        }
    }

    private BootstrapMethod(int bootstrap_method_ref, int num_bootstrap_arguments) {
        this(bootstrap_method_ref, new int[num_bootstrap_arguments]);
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

    @Override
    public String toString() {
        return "BootstrapMethod [bootstrapMethodRef=" + bootstrapMethodRef + ", bootstrapArguments=" + Arrays.toString(bootstrapArguments) + "]";
    }
}
