package org.apache.bcel.generic;

import java.io.IOException;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.LocalVariableInstruction;
import org.apache.bcel.util.ByteSequence;

public class IINC extends LocalVariableInstruction {
    private boolean wide;
    private int c;

    public IINC() {
    }

    public IINC(final int n, final int c) {
        super();
        super.setOpcode(InstructionOpCodes.IINC);
        super.setLength((short) 3);
        // setIndex(n);
        // setIncrement(c);
    }
//    @Override
//    public void accept(final Visitor v) {
//        v.visitLocalVariableInstruction(this);
//        v.visitIINC(this);
//    }
//
//    @Override
//    public void dump(final DataOutputStream out) throws IOException {
//        if (wide) {
//            out.writeByte(InstructionOpCodes.WIDE.getOpcode());
//        }
//        out.writeByte(super.getOpcode().getOpcode());
//        if (wide) {
//            out.writeShort(super.getIndex());
//            out.writeShort(c);
//        } else {
//            out.writeByte(super.getIndex());
//            out.writeByte(c);
//        }
//    }

    public final int getIncrement() {
        return c;
    }
//    @Override
//    public Type getType(final ConstantPoolGen cp) {
//        return Type.INT;
//    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        this.wide = wide;
        if (wide) {
            super.setLength(6);
            super.setIndexOnly(bytes.readUnsignedShort());
            c = bytes.readShort();
        } else {
            super.setLength(3);
            super.setIndexOnly(bytes.readUnsignedByte());
            c = bytes.readByte();
        }
    }
//    public final void setIncrement(final int c) {
//        this.c = c;
//        setWide();
//    }
//    @Override
//    public final void setIndex(final int n) {
//        if (n < 0) {
//            throw new ClassGenException("Negative index value: " + n);
//        }
//        super.setIndexOnly(n);
//        setWide();
//    }
//
//    private void setWide() {
//        wide = super.getIndex() > org.apache.bcel.Const.MAX_BYTE;
//        if (c > 0) {
//            wide = wide || (c > Byte.MAX_VALUE);
//        } else {
//            wide = wide || (c < Byte.MIN_VALUE);
//        }
//        if (wide) {
//            super.setLength(6);
//        } else {
//            super.setLength(3);
//        }
//    }

    @Override
    public String toString(final boolean verbose) {
        return super.toString(verbose) + " " + c;
    }
}
