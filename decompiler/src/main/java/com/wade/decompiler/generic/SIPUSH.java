package com.wade.decompiler.generic;

import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.ConstantPushInstruction;
import com.wade.decompiler.generic.base.Instruction;
import com.wade.decompiler.generic.type.Type;
import com.wade.decompiler.util.ByteSequence;

public class SIPUSH extends Instruction implements ConstantPushInstruction {
    private short b;

    public SIPUSH() {
    }

    public SIPUSH(short b, ConstantPool cp) {
        super(InstructionOpCodes.SIPUSH, 3, cp);
        this.b = b;
    }

    @Override
    public Type getType(ConstantPool cp) {
        return Type.SHORT;
    }

    @Override
    public Number getValue() {
        return Integer.valueOf(b);
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        super.setLength(3);
        b = bytes.readShort();
    }

    @Override
    public String toString() {
        return super.toString() + " " + b;
    }
}
