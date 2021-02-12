
package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;
import org.apache.bcel.enums.ClassFileConstants;

public final class NestHost extends Attribute {

    private int hostClassIndex;

    NestHost(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, 0, constant_pool);
        hostClassIndex = input.readUnsignedShort();
    }

    public NestHost(final int nameIndex, final int length, final int hostClassIndex, final ConstantPool constantPool) {
        super(Const.ATTR_NEST_MEMBERS, nameIndex, length, constantPool);
        this.hostClassIndex = hostClassIndex;
    }

    public NestHost(final NestHost c) {
        this(c.getNameIndex(), c.getLength(), c.getHostClassIndex(), c.getConstantPool());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitNestHost(this);
    }

    @Override
    public Attribute copy(final ConstantPool _constant_pool) {
        final NestHost c = (NestHost) clone();
        c.setConstantPool(_constant_pool);
        return c;
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(hostClassIndex);
    }

    public int getHostClassIndex() {
        return hostClassIndex;
    }

    public void setHostClassIndex(final int hostClassIndex) {
        this.hostClassIndex = hostClassIndex;
    }

    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();
        buf.append("NestHost: ");
        final String class_name = super.getConstantPool().getConstantString(hostClassIndex, ClassFileConstants.CONSTANT_Class);
        buf.append(Utility.compactClassName(class_name, false));
        return buf.toString();
    }
}
