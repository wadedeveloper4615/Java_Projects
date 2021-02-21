package com.wade.decompiler.classfile.instructions;

import com.wade.decompiler.classfile.constant.ConstantPool;
import com.wade.decompiler.classfile.instructions.base.StoreInstruction;
import com.wade.decompiler.enums.InstructionOpCodes;

public class FSTORE extends StoreInstruction {
    public FSTORE(ConstantPool cp) {
        super(InstructionOpCodes.FSTORE, InstructionOpCodes.FSTORE_0, cp);
    }

    public FSTORE(int n, ConstantPool cp) {
        super(InstructionOpCodes.FSTORE, InstructionOpCodes.FSTORE_0, n, cp);
    }
}
