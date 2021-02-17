package com.wade.decompiler.generic;

import java.io.IOException;

import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.util.ByteSequence;

public class LDC_W extends LDC {
    public LDC_W() {
    }

    public LDC_W(int index) {
        super(index);
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        setIndex(bytes.readUnsignedShort());
        super.setOpcode(InstructionOpCodes.LDC_W);
        super.setLength(3);
    }
}
