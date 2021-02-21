package com.wade.decompiler.classfile.instructions.base;

import java.io.IOException;

import com.wade.decompiler.util.ByteSequence;

public interface InstructionInit {
    void initFromFile(ByteSequence bytes, boolean wide) throws IOException;
}
