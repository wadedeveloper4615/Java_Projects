package com.wade.decompiler.generic;

import java.io.IOException;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.enums.InstructionOpCodes;
import com.wade.decompiler.generic.base.InstructionHandle;
import com.wade.decompiler.generic.base.JsrInstruction;
import com.wade.decompiler.util.ByteSequence;

public class JSR_W extends JsrInstruction {
    public JSR_W() {
    }

    public JSR_W(InstructionHandle target, ConstantPool cp) {
        super(InstructionOpCodes.JSR_W, target, cp);
        super.setLength(5);
    }

    @Override
    public void initFromFile(ByteSequence bytes, boolean wide) throws IOException {
        super.setIndex(bytes.readInt());
        super.setLength(5);
    }
}
