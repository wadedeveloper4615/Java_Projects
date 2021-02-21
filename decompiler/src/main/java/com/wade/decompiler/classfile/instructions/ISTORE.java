package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.StoreInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class ISTORE extends StoreInstruction {
    public ISTORE(ConstantPool cp) {
        super(InstructionOpCodes.ISTORE, InstructionOpCodes.ISTORE_0, cp);
    }

    public ISTORE(int n, ConstantPool cp) {
        super(InstructionOpCodes.ISTORE, InstructionOpCodes.ISTORE_0, n, cp);
    }
}
