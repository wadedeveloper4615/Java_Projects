package com.wade.decompiler.generic.base;

import java.io.IOException;

import com.wade.decompiler.util.ByteSequence;

public interface InstructionInit {
    void initFromFile(ByteSequence bytes, boolean wide) throws IOException;
}
