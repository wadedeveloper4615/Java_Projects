package com.wade.decompiler.classfile.instructions;

import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.JsrInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.util.ByteSequence;

public class JSR_W extends JsrInstruction {
    public JSR_W(ConstantPool cp) {
        super(InstructionOpCodes.JSR_W, cp);
        super.setLength(5);
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        super.setIndex(bytes.readInt());
        super.setLength(5);
    }
}