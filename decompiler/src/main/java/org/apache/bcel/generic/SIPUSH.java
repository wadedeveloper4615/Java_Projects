package org.apache.bcel.generic;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.ConstantPushInstruction;
import org.apache.bcel.generic.base.Instruction;

public class SIPUSH extends Instruction implements ConstantPushInstruction {
    private short b;

    public SIPUSH() {
    }

    public SIPUSH(final short b) {
        super(InstructionOpCodes.SIPUSH, (short) 3);
        this.b = b;
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitPushInstruction(this);
//        v.visitStackProducer(this);
//        v.visitTypedInstruction(this);
//        v.visitConstantPushInstruction(this);
//        v.visitSIPUSH(this);
//    }
//
//    @Override
//    public void dump(final DataOutputStream out) throws IOException {
//        super.dump(out);
//        out.writeShort(b);
//    }
//
//    @Override
//    public Type getType(final ConstantPoolGen cp) {
//        return Type.SHORT;
//    }

    @Override
    public Number getValue() {
        return Integer.valueOf(b);
    }
//
//    @Override
//    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
//        super.setLength(3);
//        b = bytes.readShort();
//    }
//
//    @Override
//    public String toString(final boolean verbose) {
//        return super.toString(verbose) + " " + b;
//    }
}
