package org.apache.bcel.classfile.attribute;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;
import org.apache.bcel.classfile.Visitor;
import org.apache.bcel.classfile.constant.ConstantPool;

public final class Deprecated extends Attribute {
    private byte[] bytes;

    public Deprecated(final Deprecated c) {
        this(c.getNameIndex(), c.getLength(), c.getBytes(), c.getConstantPool());
    }

    public Deprecated(final int name_index, final int length, final byte[] bytes, final ConstantPool constant_pool) {
        super(Const.ATTR_DEPRECATED, name_index, length, constant_pool);
        this.bytes = bytes;
    }

    public Deprecated(final int name_index, final int length, final DataInput input, final ConstantPool constant_pool) throws IOException {
        this(name_index, length, (byte[]) null, constant_pool);
        if (length > 0) {
            bytes = new byte[length];
            input.readFully(bytes);
            println("Deprecated attribute with length > 0");
        }
    }

    @Override
    public void accept(final Visitor v) {
        v.visitDeprecated(this);
    }

    @Override
    public Attribute copy(final ConstantPool _constant_pool) {
        final Deprecated c = (Deprecated) clone();
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
        return Const.getAttributeName(Const.ATTR_DEPRECATED);
    }
}
