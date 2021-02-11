
package org.apache.bcel.classfile;

import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.bcel.Const;

public final class ConstantInteger extends Constant implements ConstantObject {

    private int bytes;

    public ConstantInteger(final int bytes) {
        super(Const.CONSTANT_Integer);
        this.bytes = bytes;
    }

    public ConstantInteger(final ConstantInteger c) {
        this(c.getBytes());
    }

    ConstantInteger(final DataInput file) throws IOException {
        this(file.readInt());
    }

    @Override
    public void accept(final Visitor v) {
        v.visitConstantInteger(this);
    }

    @Override
    public void dump(final DataOutputStream file) throws IOException {
        file.writeByte(super.getTag());
        file.writeInt(bytes);
    }

    public int getBytes() {
        return bytes;
    }

    public void setBytes(final int bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return super.toString() + "(bytes = " + bytes + ")";
    }

    @Override
    public Object getConstantValue(final ConstantPool cp) {
        return Integer.valueOf(bytes);
    }
}
