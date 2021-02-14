package org.apache.bcel.generic;

import java.io.IOException;

import org.apache.bcel.enums.InstructionOpCodes;
import org.apache.bcel.generic.base.ConstantPushInstruction;
import org.apache.bcel.generic.base.Instruction;
import org.apache.bcel.util.ByteSequence;

public class BIPUSH extends Instruction implements ConstantPushInstruction {
    private byte b;

    public BIPUSH() {
    }

    public BIPUSH(final byte b) {
        super(InstructionOpCodes.BIPUSH, (short) 2);
        this.b = b;
    }
//
//    @Override
//    public void accept(final Visitor v) {
//        v.visitPushInstruction(this);
//        v.visitStackProducer(this);
//        v.visitTypedInstruction(this);
//        v.visitConstantPushInstruction(this);
//        v.visitBIPUSH(this);
//    }
//    @Override
//    public void dump(final DataOutputStream out) throws IOException {
//        super.dump(out);
//        out.writeByte(b);
//    }
//    @Override
//    public Type getType(final ConstantPoolGen cp) {
//        return Type.BYTE;
//    }

    @Override
    public Number getValue() {
        return Integer.valueOf(b);
    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.setLength(2);
        b = bytes.readByte();
    }

    @Override
    public String toString(final boolean verbose) {
        return super.toString(verbose) + " " + b;
    }
}
