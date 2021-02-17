package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.Const;
import com.wade.decompiler.generic.base.ConstantPushInstruction;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.base.Type;
import com.wade.decompiler.generic.gen.ConstantPoolGen;
import com.wade.decompiler.generic.gen.Visitor;
import com.wade.decompiler.util.ByteSequence;

public class BIPUSH extends Instruction implements ConstantPushInstruction {
    private byte b;

    public BIPUSH() {
    }

    public BIPUSH(final byte b) {
        super(Const.BIPUSH, (short) 2);
        this.b = b;
    }

    @Override
    public void accept(final Visitor v) {
        v.visitPushInstruction(this);
        v.visitStackProducer(this);
        v.visitTypedInstruction(this);
        v.visitConstantPushInstruction(this);
        v.visitBIPUSH(this);
    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        super.dump(out);
        out.writeByte(b);
    }

    @Override
    public Type getType(final ConstantPoolGen cp) {
        return Type.BYTE;
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(b);
    }

    @Override
    public void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        super.setLength(2);
        b = bytes.readByte();
    }

    @Override
    public String toString(final boolean verbose) {
        return super.toString(verbose) + " " + b;
    }
}
