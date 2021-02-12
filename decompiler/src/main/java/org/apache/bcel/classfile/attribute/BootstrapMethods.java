
package org.apache.bcel.classfile.attribute;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;
import org.apache.bcel.classfile.BootstrapMethod;
import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.classfile.constant.ConstantPool;

public class BootstrapMethods extends Attribute {

    private BootstrapMethod[] bootstrapMethods; // TODO this could be made final (setter is not used)

    public BootstrapMethods(final BootstrapMethods c) {
        this(c.getNameIndex(), c.getLength(), c.getBootstrapMethods(), c.getConstantPool());
    }

    public BootstrapMethods(final int name_index, final int length, final BootstrapMethod[] bootstrapMethods, final ConstantPool constant_pool) {
        super(Const.ATTR_BOOTSTRAP_METHODS, name_index, length, constant_pool);
        this.bootstrapMethods = bootstrapMethods;
    }

    public BootstrapMethods(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, (BootstrapMethod[]) null, constant_pool);

        final int num_bootstrap_methods = input.readUnsignedShort();
        bootstrapMethods = new BootstrapMethod[num_bootstrap_methods];
        for (int i = 0; i < num_bootstrap_methods; i++) {
            bootstrapMethods[i] = new BootstrapMethod(input);
        }
    }

    @Override
    public void accept(final Visitor v) {
        v.visitBootstrapMethods(this);
    }

    @Override
    public BootstrapMethods copy(final ConstantPool _constant_pool) {
        final BootstrapMethods c = (BootstrapMethods) clone();
        c.bootstrapMethods = new BootstrapMethod[bootstrapMethods.length];

        for (int i = 0; i < bootstrapMethods.length; i++) {
            c.bootstrapMethods[i] = bootstrapMethods[i].copy();
        }
        c.setConstantPool(_constant_pool);
        return c;
    }

    @Override
    public final void dump(final DataOutputStream file) throws IOException {
        super.dump(file);

        file.writeShort(bootstrapMethods.length);
        for (final BootstrapMethod bootstrap_method : bootstrapMethods) {
            bootstrap_method.dump(file);
        }
    }

    public final BootstrapMethod[] getBootstrapMethods() {
        return bootstrapMethods;
    }

    public final void setBootstrapMethods(final BootstrapMethod[] bootstrapMethods) {
        this.bootstrapMethods = bootstrapMethods;
    }

    @Override
    public final String toString() {
        final StringBuilder buf = new StringBuilder();
        buf.append("BootstrapMethods(");
        buf.append(bootstrapMethods.length);
        buf.append("):");
        for (int i = 0; i < bootstrapMethods.length; i++) {
            buf.append("\n");
            final int start = buf.length();
            buf.append("  ").append(i).append(": ");
            final int indent_count = buf.length() - start;
            final String[] lines = (bootstrapMethods[i].toString(super.getConstantPool())).split("\\r?\\n");
            buf.append(lines[0]);
            for (int j = 1; j < lines.length; j++) {
                buf.append("\n").append("          ".substring(0, indent_count)).append(lines[j]);
            }
        }
        return buf.toString();
    }
}
