
package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.bcel.classfile.constant.ConstantPool;
import org.apache.bcel.enums.ClassFileConstants;

public class BootstrapMethod implements Cloneable {

    private int bootstrapMethodRef;

    private int[] bootstrapArguments;

    public BootstrapMethod(final BootstrapMethod c) {
        this(c.getBootstrapMethodRef(), c.getBootstrapArguments());
    }

    BootstrapMethod(final DataInput input) throws IOException {
        this(input.readUnsignedShort(), input.readUnsignedShort());

        for (int i = 0; i < bootstrapArguments.length; i++) {
            bootstrapArguments[i] = input.readUnsignedShort();
        }
    }

    // helper method
    private BootstrapMethod(final int bootstrap_method_ref, final int num_bootstrap_arguments) {
        this(bootstrap_method_ref, new int[num_bootstrap_arguments]);
    }

    public BootstrapMethod(final int bootstrapMethodRef, final int[] bootstrapArguments) {
        this.bootstrapMethodRef = bootstrapMethodRef;
        this.bootstrapArguments = bootstrapArguments;
    }

    public BootstrapMethod copy() {
        try {
            return (BootstrapMethod) clone();
        } catch (final CloneNotSupportedException e) {
            // TODO should this throw?
        }
        return null;
    }

    public final void dump(final DataOutputStream file) throws IOException {
        file.writeShort(bootstrapMethodRef);
        file.writeShort(bootstrapArguments.length);
        for (final int bootstrap_argument : bootstrapArguments) {
            file.writeShort(bootstrap_argument);
        }
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

    public void setBootstrapArguments(final int[] bootstrapArguments) {
        this.bootstrapArguments = bootstrapArguments;
    }

    public void setBootstrapMethodRef(final int bootstrapMethodRef) {
        this.bootstrapMethodRef = bootstrapMethodRef;
    }

    @Override
    public final String toString() {
        return "BootstrapMethod(" + bootstrapMethodRef + ", " + bootstrapArguments.length + ", " + Arrays.toString(bootstrapArguments) + ")";
    }

    public final String toString(final ConstantPool constantPool) {
        final StringBuilder buf = new StringBuilder();
        String bootstrap_method_name;
        bootstrap_method_name = constantPool.constantToString(bootstrapMethodRef, ClassFileConstants.CONSTANT_MethodHandle);
        buf.append(Utility.compactClassName(bootstrap_method_name, false));
        final int num_bootstrap_arguments = bootstrapArguments.length;
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
