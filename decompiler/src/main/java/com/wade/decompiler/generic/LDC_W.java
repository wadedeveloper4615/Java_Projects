package com.wade.decompiler.generic;

import java.io.IOException;

import com.wade.decompiler.Const;
import com.wade.decompiler.util.ByteSequence;

public class LDC_W extends LDC {
    LDC_W() {
    }

    public LDC_W(final int index) {
        super(index);
    }

    @Override
    protected void initFromFile(final ByteSequence bytes, final boolean wide) throws IOException {
        setIndex(bytes.readUnsignedShort());
        // Override just in case it has been changed
        super.setOpcode(Const.LDC_W);
        super.setLength(3);
    }
}
