package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.StoreInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class LSTORE extends StoreInstruction {
    public LSTORE(ConstantPool cp) {
        super(InstructionOpCodes.LSTORE, InstructionOpCodes.LSTORE_0, cp);
    }

    public LSTORE(int n, ConstantPool cp) {
        super(InstructionOpCodes.LSTORE, InstructionOpCodes.LSTORE_0, n, cp);
    }
}
