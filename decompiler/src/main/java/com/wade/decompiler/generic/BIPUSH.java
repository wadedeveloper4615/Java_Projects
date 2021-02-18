package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConstantPushInstruction;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.type.Type;
import com.wade.decompiler.util.ByteSequence;

public class BIPUSH extends Instruction implements ConstantPushInstruction {
    private byte b;

    public BIPUSH() {
    }

    public BIPUSH(byte b) {
        super(InstructionOpCodes.BIPUSH, 2);
        this.b = b;
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        super.dump(out);
        out.writeByte(b);
    }

    @Override
    public Type getType(ConstantPool cp) {
        return Type.BYTE;
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(b);
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        super.setLength(2);
        b = bytes.readByte();
    }

    @Override
    public String toString(boolean verbose) {
        return super.toString(verbose) + " " + b;
    }
}
