package org.apache.bcel.classfile.attribute;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;
import org.apache.bcel.classfile.Utility;
import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.classfile.constant.ConstantPool;

public final class Synthetic extends Attribute {
    private byte[] bytes;

    public Synthetic(final int name_index, final int length, final byte[] bytes, final ConstantPool constant_pool) {
        super(Const.ATTR_SYNTHETIC, name_index, length, constant_pool);
        this.bytes = bytes;
    }

    public Synthetic(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, (byte[]) null, constant_pool);
        if (length > 0) {
            bytes = new byte[length];
            input.readFully(bytes);
            println("Synthetic attribute with length > 0");
        }
    }

    public Synthetic(final Synthetic c) {
        this(c.getNameIndex(), c.getLength(), c.getBytes(), c.getConstantPool());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitSynthetic(this);
    }

    @Override
    public Attribute copy(final ConstantPool _constant_pool) {
        final Synthetic c = (Synthetic) clone();
        if (bytes != null) {
            c.bytes = new byte[bytes.length];
            System.arraycopy(bytes, 0, c.bytes, 0, bytes.length);
        }
        c.setConstantPool(_constant_pool);
        return c;
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        if (super.getLength() > 0) {
            file.write(bytes, 0, super.getLength());
        }
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(final byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder("Synthetic");
        if (super.getLength() > 0) {
            buf.append(" ").append(Utility.toHexString(bytes));
        }
        return buf.toString();
    }
}
