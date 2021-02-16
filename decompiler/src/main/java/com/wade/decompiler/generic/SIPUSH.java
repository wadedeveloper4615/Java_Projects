package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.util.ByteSequence;

public class SIPUSH extends Instruction implements ConstantPushInstruction {
    private short b;

    SIPUSH() {
    }

    public SIPUSH(final short b) {
        super(com.wade.decompiler.Const.SIPUSH, (short) 3);
        this.b = b;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitPushInstruction(this);
        v.visitStackProducer(this);
        v.visitTypedInstruction(this);
        v.visitConstantPushInstruction(this);
        v.visitSIPUSH(this);
    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        super.dump(out);
        out.writeShort(b);
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.SHORT;
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(b);
    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.setLength(3);
        b = bytes.readShort();
    }

    @Override
    public String toString(final boolean verbose) {
        return super.toString(verbose) + " " + b;
    }
}
