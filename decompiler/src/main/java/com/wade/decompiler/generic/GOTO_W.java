package com.wade.decompiler.generic;

import java.io.DataOutputStream;
import java.io.IOException;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.GotoInstruction;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.util.ByteSequence;

public class GOTO_W extends GotoInstruction {
    public GOTO_W() {
    }

    public GOTO_W(InstructionHandle target) {
        super(InstructionOpCodes.GOTO_W, target);
        super.setLength(5);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        super.setIndex(getTargetOffset());
        out.writeByte(super.getOpcode().getOpcode());
        out.writeInt(super.getIndex());
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        super.setIndex(bytes.readInt());
        super.setLength(5);
    }
}
