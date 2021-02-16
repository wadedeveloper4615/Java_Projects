package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.util.ByteSequence;

public class RET extends Instruction implements IndexedInstruction, TypedInstruction {
    private boolean wide;
    private int index; // index to local variable containg the return address

    RET() {
    }

    public RET(final int index) {
        super(com.wade.decompiler.Const.RET, (short) 2);
        setIndex(index); // May set wide as side effect
    }

    @Override
    public void accept(final Visitor v) {
        v.visitRET(this);
    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        if (wide) {
            out.writeByte(com.wade.decompiler.Const.WIDE);
        }
        out.writeByte(super.getOpcode());
        if (wide) {
            out.writeShort(index);
        } else {
            out.writeByte(index);
        }
    }

    @Override
    public final int getIndex() {
        return index;
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        return ReturnaddressType.NO_TARGET;
    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        this.wide = wide;
        if (wide) {
            index = bytes.readUnsignedShort();
            super.setLength(4);
        } else {
            index = bytes.readUnsignedByte();
            super.setLength(2);
        }
    }

    @Override
    public final void setIndex(final int n) {
        if (n < 0) {
            throw new ClassGenException("Negative index value: " + n);
        }
        index = n;
        setWide();
    }

    private void setWide() {
        wide = index > com.wade.decompiler.Const.MAX_BYTE;
        if (wide) {
            super.setLength(4); // Including the wide byte
        } else {
            super.setLength(2);
        }
    }

    @Override
    public String toString(final boolean verbose) {
        return super.toString(verbose) + " " + index;
    }
}
